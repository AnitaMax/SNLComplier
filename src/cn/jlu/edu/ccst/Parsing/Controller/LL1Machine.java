package cn.jlu.edu.ccst.Parsing.Controller;

import cn.jlu.edu.ccst.Parsing.Model.AnalyseLog;
import cn.jlu.edu.ccst.Parsing.Model.AnalyseResult;
import cn.jlu.edu.ccst.Parsing.Model.ProductionElement;
import cn.jlu.edu.ccst.Parsing.Model.Tree;
import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LL1Machine {
    ProductionElementController productionElementController;

    public Tree getTree() {
        return tree;
    }

    Tree tree;








    public LL1Machine() {

        Node root=new Node("Program");
        tree=new Tree(root);
        productionElementController=new ProductionElementController("productionLines.txt");
        productionElementController.iniFirstSet();
        productionElementController.iniFollowSet();
        productionElementController.setProductionPredict();
        productionElementController.printProductions();
    }


    AnalyseLog getAnalyseLog(Token token,Stack<ProductionElement> analysingStack,String result){
        //记录过程
        var log=new AnalyseLog();
        var stackStatus=new StringBuilder();
        analysingStack.forEach(ele-> stackStatus.insert(0,ele.getContent()+" "));
        log.setStack(stackStatus.toString());
        if(token!=null)
            log.setInput("line: "+token.getRow()+" "+token.getType()+" "+token.getValue());
        else
            log.setInput(" ");
        log.setAction(result);
        return  log;
    }
    public AnalyseResult parsing(List<Token> tokens, ProductionElement startEle){
        var result=new AnalyseResult();



        //初始化输入栈
        Stack<ProductionElement> analysingStack=new Stack<>();
        analysingStack.push(productionElementController.getSNLElement("$"));
        analysingStack.push(startEle);
        for (int i = 0; i < tokens.size(); i++) {

            var token=tokens.get(i);
            var stackTop=analysingStack.peek();
            //如果栈是空的，但是还有输入
            if(analysingStack.size()<=0){
                result.getLogs().add(getAnalyseLog(token,analysingStack,"栈空但是还有输入！"));
                result.setSuccess(false);
                result.setFailResult("栈空但是还有输入！");
                return result;
            }

            //栈顶是终极符，与输入相同，Match
            if(stackTop.isEnd()){
                if(stackTop.accept(token)){
                    result.getLogs().add(getAnalyseLog(token,analysingStack,"匹配"));
                    analysingStack.pop();
                }
                else {
                    result.getLogs().add(getAnalyseLog(token,analysingStack,"栈顶不匹配"));
                    result.setSuccess(false);
                    result.setFailResult("栈顶不匹配");
                    return result;
                }
            }
            //栈顶是非终极符，根据输入的终极符展开
            else{
                var ele=productionElementController.getElementHashMap().get(stackTop.getContent());
                var productionsRalted=ele.getProductionsStartedWiththis();
                boolean success=false;//是否有对应的产生式可以匹配
                for (var producton:productionsRalted) {
                    if(producton.getPredict()==null||producton.getPredict().isEmpty()){
                        System.out.println("产生式 "+producton+" predict集为空！");
                    }else {
                        for (var pred : producton.getPredict()) {
                            if (!success&&pred.accept(token)) {
                                success = true;
                                result.getLogs().add(getAnalyseLog(token, analysingStack, "替换 " + producton.toString()));


                                //树形结构
                                ArrayList<Node> leafNodes=tree.getLeafNode();
                                String l_name=producton.getLeft().getContent();
                                for(int k=0;k<leafNodes.size();k++){
                                    Node rootNode=leafNodes.get(k);
                                    if(rootNode.getName().equals(l_name)){
                                        for(int j=0;j<producton.getRight().size();j++){
                                            String curNodeContent=producton.getRight().get(j).getContent();
                                            Node curNode=new Node(curNodeContent);
                                            rootNode.add(curNode);
                                        }
                                    }
                                }




                                //




                                //产生式是否是EPSILON
                                if (producton.getRight().get(0).getContent().equals("EPSILON")) {
                                    analysingStack.pop();
                                } else {
                                    analysingStack.pop();
                                    for (int m=producton.getRight().size()-1;m>=0;m--) {
                                        analysingStack.push(producton.getRight().get(m));
                                    }
                                }
                                i--;
                                break;
                            }
                        }
                    }
                }
                if(!success){
                    result.getLogs().add(getAnalyseLog(token,analysingStack,"没有相应的产生式"));
                    result.setSuccess(false);
                    result.setFailResult("没有相应的产生式 Token："+token);
                    return result;
                }
            }
        }
        if(analysingStack.isEmpty()){
            result.getLogs().add(getAnalyseLog(null,analysingStack,"成功！"));
            result.setSuccess(true);
            return result;
        }
        result.setSuccess(false);
        result.setFailResult("输入内容不全！分析栈非空！");
        return result;
    }
}
