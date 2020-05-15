package cn.jlu.edu.ccst.Parsing.Controller;

import cn.jlu.edu.ccst.Parsing.Model.Production;
import cn.jlu.edu.ccst.Parsing.Model.ProductionElement;
import cn.jlu.edu.ccst.Parsing.Model.SNLProdcutionElement;
import cn.jlu.edu.ccst.Parsing.Util.FileReaderUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductionElementController {
    HashMap<String, ProductionElement> elementHashMap=new HashMap<>();//提供非终极符、INTC、ID、分隔符等 到元素的映射，进而找到相应的产生式。
    ArrayList<Production> productions=new ArrayList<>();

    public HashMap<String, ProductionElement> getElementHashMap() {
        return elementHashMap;
    }

    public void setElementHashMap(HashMap<String, ProductionElement> elementHashMap) {
        this.elementHashMap = elementHashMap;
    }

    public ArrayList<Production> getProductions() {
        return productions;
    }

    public void setProductions(ArrayList<Production> productions) {
        this.productions = productions;
    }

    public ProductionElementController() {
    }

    //Todo :从path中读取产生式并保存到hashmap中
    public ProductionElementController(String path) {

        var lines= FileReaderUtil.readFile("../productionLines.txt");
        for (var line:lines) {
            var production=createProduction(line);
            productions.add(production);
            System.out.println(production.toString());
        }
        System.out.println("\n读取产生式"+productions.size()+"条");
    }

    public void SetElementFisrt(String elementString){
        var element=elementHashMap.get(elementString);

        element.setFirstSet(null);
    }
    public void SetElementFollow(String elementString){
        var element=elementHashMap.get(elementString);

        element.setFollowSet(null);
    }

    public void setProductionPredict(){
        for (var production:productions) {
            production.setPredict(null);
        }
    }
    //完成:实现从产生式字符串构造Production
    /**
     * 从产生式字符串构造Production
     * 注意：表示空串的“yifusiluo”艾普西龙 写作 EPSILON
     * 产生式中大写单词表示终极符，只有首字母大写的是非终极符
     * @param productionString 产生式字符串 例如	 ProcName	 ::=	ID
     */
    public Production createProduction(String productionString)  {
        //expmale:ConditionalStm->IF RelExp THEN StmList ELSE StmList FI
        //去掉左右空格
        productionString=productionString.trim();
        //划分左右
        String[] elements=productionString.split("->");
        if(elements.length==2){
            String left=elements[0];
            String[] right=elements[1].split(" ");
            //处理左部
            var leftElement=getElement(left);
            if(leftElement.isEnd())
                throw new RuntimeException(productionString+"产生式左部错误");
            //处理右部
            ArrayList<ProductionElement> rightElements=new ArrayList<>();
            for(var element :right){
                var rightElement=getElement(element);
                rightElements.add(rightElement);
            }
            System.out.print("\n");
            //创建产生式类
            var production=new Production(leftElement,rightElements);
            //将产生式关联到左部的元素中
            leftElement.getProductionsStartedWiththis().add(production);
            //返回结果
            return production;
        }else{
            throw new RuntimeException("产生式"+productionString+"不正确");
        }
    }

    public ProductionElement getElement(String content){
        if(!elementHashMap.containsKey(content)){
            elementHashMap.put(content,new SNLProdcutionElement(content));
        }
        return elementHashMap.get(content);
    }

}
