package cn.jlu.edu.ccst.Parsing.Controller;

import cn.jlu.edu.ccst.Parsing.Model.Production;
import cn.jlu.edu.ccst.Parsing.Model.ProductionElement;
import cn.jlu.edu.ccst.Parsing.Model.SNLProdcutionElement;
import cn.jlu.edu.ccst.Parsing.Util.FileReaderUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductionElementController {
    HashMap<String, ProductionElement> elementHashMap=new HashMap<>();//提供非终极符、INTC、ID、分隔符等 到元素的映射，进而找到相应的产生式。
    boolean isFinish;
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
            //System.out.println(production.toString());
        }
        System.out.println("\n读取产生式"+productions.size()+"条");
    }
    public void iniFirstSet(){
        isFinish=false;
        if(!isFinish){
            for (Map.Entry<String, ProductionElement> entry : elementHashMap.entrySet()) {
                SetElementFisrt(entry.getKey());
            }
        }


    }
    public ArrayList<ProductionElement> findB(Production p){
        ArrayList<ProductionElement> right=p.getRight();
        ArrayList<ProductionElement> BList=new ArrayList<ProductionElement>();
        for(int i=0;i<right.size();i++){
            Boolean matchB=true;
            if(right.get(i).isEnd()){
                matchB=false;
            }
            for(int j=i+1;j<right.size();j++){
                ProductionElement curP=right.get(j);
                if(!curP.getFirstSet().contains(elementHashMap.get("EPSILON"))){
                    matchB=false;
                }
            }
            if(matchB){
                BList.add(right.get(i));
            }
        }
        return BList;

    }
    public ArrayList<ProductionElement> getFirstBETA(ArrayList<ProductionElement> right){
        ArrayList<ProductionElement> firstBETA=new ArrayList<ProductionElement>();
        int index=0;
        for(int i=0;i<right.size();i++){
            ProductionElement curElement=right.get(i);
            if(curElement.getFirstSet().contains(elementHashMap.get("EPSILON"))){
                index=i;
            }
        }
        for(int i=0;i<=index;i++){
            ArrayList<ProductionElement> firstSet=right.get(i).getFirstSet();
            for(int j=0;j<firstSet.size();j++){
                if(firstSet.get(j)!=elementHashMap.get("EPSILON")){
                    firstBETA.add(firstSet.get(j));
                }
            }
        }
        return firstBETA;
    }
    public void iniFollowSet(){
        var element=elementHashMap.get("Program");
        ArrayList<ProductionElement> followSet=new ArrayList<ProductionElement>();
        followSet.add(elementHashMap.get("$"));
        isFinish=false;
        element.setFollowSet(followSet);
        if(!isFinish){
            for (Map.Entry<String, ProductionElement> entry : elementHashMap.entrySet()) {
                ProductionElement A=entry.getValue();
                ArrayList<Production> productions=A.getProductionsStartedWiththis();
                for(int i=0;i<productions.size();i++){//对于所有的产生式做以下操作
                    Production curProduction=productions.get(i);
                    ArrayList<ProductionElement> right=productions.get(i).getRight();
                    for(int k=0;k<right.size()-1;k++){
                        ProductionElement cur_ele=right.get(k);
                        if(!cur_ele.isEnd()){
                            ArrayList<ProductionElement> restP=new ArrayList<ProductionElement>();
                            for(int k1=k;k1<right.size();k1++){
                                restP.add(right.get(k1));
                            }
                            ArrayList<ProductionElement> firstBETA=getFirstBETA(restP);
                            ArrayList<ProductionElement> cur_followSet=cur_ele.getFollowSet();
                            cur_followSet.addAll(firstBETA);
                            cur_ele.setFollowSet(cur_followSet);
                        }
                    }


                    ArrayList<ProductionElement> Blist=findB(curProduction);
                    for(int j=0;j<Blist.size();j++){
                        ArrayList<ProductionElement> curFollowSet=Blist.get(j).getFollowSet();
                        curFollowSet.addAll(A.getFollowSet());
                    }


                }
            }
        }


    }

    public void SetElementFisrt(String elementString){
        isFinish=true;
        var element=elementHashMap.get(elementString);
        ArrayList<ProductionElement> firstSet;
        if(element.getFirstSet()==null){
            firstSet=new ArrayList<ProductionElement>();
        }else{
            firstSet=element.getFirstSet();
        }
        if(element.isEnd()){

            if(!firstSet.contains(element)){
                isFinish=false;
                firstSet.add(element);
            }

        }else{
            ArrayList<Production> productionList=element.getProductionsStartedWiththis();
            for(int i=0;i<productionList.size();i++){
                Production p=productionList.get(i);
                ArrayList<ProductionElement> right=p.getRight();
                if(right.get(0)==elementHashMap.get("EPSILON")){

                    if(!firstSet.contains(elementHashMap.get("EPSILON"))){
                        isFinish=false;
                        firstSet.add(elementHashMap.get("EPSILON"));//如果是空产生式就加入EPSILON
                    }


                }else{
                    //如果不是空产生式

                    for(int j=0;j<right.size();j++){

                        ProductionElement ele=right.get(j);

                        if(ele.isEnd()){


                            if(!firstSet.contains(right.get(j))){
                                isFinish=false;
                                firstSet.add(right.get(j));
                            }

                            break;
                        }else{
                            for(int k=0;k<ele.getFirstSet().size();k++){//如果是非终结符，就把他的first集加入

                                if(!firstSet.contains(ele.getFirstSet().get(k))){
                                    isFinish=false;
                                    firstSet.add(ele.getFirstSet().get(k));
                                }

                            }
                            if(!ele.getFirstSet().contains(elementHashMap.get("EPSILON"))){//如果含有EPSILON，要考虑下一符号，如果没有就不应再添加了
                                break;
                            }

                        }



                    }
                }

            }

        }
        element.setFirstSet(firstSet);

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
            var leftElement= getSNLElement(left);
            if(leftElement.isEnd())
                throw new RuntimeException(productionString+"产生式左部错误");
            //处理右部
            ArrayList<ProductionElement> rightElements=new ArrayList<>();
            for(var element :right){
                var rightElement= getSNLElement(element);
                rightElements.add(rightElement);
            }
            //System.out.print("\n");
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

    public ProductionElement getSNLElement(String content){
        if(!elementHashMap.containsKey(content)){
            elementHashMap.put(content,new SNLProdcutionElement(content));
        }
        return elementHashMap.get(content);
    }

}
