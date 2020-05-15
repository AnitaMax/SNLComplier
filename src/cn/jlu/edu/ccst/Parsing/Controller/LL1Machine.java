package cn.jlu.edu.ccst.Parsing.Controller;

import cn.jlu.edu.ccst.Parsing.Model.AnalyseLog;
import cn.jlu.edu.ccst.Parsing.Model.AnalyseResult;
import cn.jlu.edu.ccst.Parsing.Model.ProductionElement;
import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;

import java.util.List;
import java.util.Stack;

public class LL1Machine {
    ProductionElementController productionElementController;

    public LL1Machine() {
        productionElementController=new ProductionElementController("../productionLines.txt");
    }


    AnalyseLog getAnalyseLog(Token token,Stack<ProductionElement> analysingStack,String result){
        //记录过程
        var log=new AnalyseLog();
        var stackStatus=new StringBuilder();
        analysingStack.forEach(ele-> stackStatus.append(ele.getContent()).append(" "));
        log.setStack(stackStatus.toString());
        if(token!=null)
            log.setInput(token.getType()+" "+token.getValue());
        else
            log.setInput(" ");
        log.setAction(result);
        return  log;
    }
    public AnalyseResult parsing(List<Token> tokens, ProductionElement startEle){
        var result=new AnalyseResult();
        //初始化输入栈
        Stack<ProductionElement> analysingStack=new Stack<>();
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
                    for (var pred:producton.getPredict()) {
                        if(pred.accept(token)){
                            success=true;
                            result.getLogs().add(getAnalyseLog(token,analysingStack,"替换"+producton.toString()));
                            //产生式是否是EPSILON
                            if(producton.getRight().get(1).getContent().equals("EPSILON")){
                                analysingStack.pop();
                            }else{
                                analysingStack.pop();
                                producton.getRight().forEach(analysingStack::push);
                            }
                            i--;
                        }
                    }
                }
                if(!success){
                    result.getLogs().add(getAnalyseLog(token,analysingStack,"没有相应的产生式"));
                    result.setSuccess(false);
                    result.setFailResult("没有相应的产生式");
                    return result;
                }
            }
        }
        if(analysingStack.isEmpty()){
            result.getLogs().add(getAnalyseLog(null,analysingStack,"成功！"));
            result.setSuccess(true);
            return result;
        }
        return result;
    }
}
