package cn.jlu.edu.ccst.Parsing.Controller;

import cn.jlu.edu.ccst.Parsing.Model.Production;
import cn.jlu.edu.ccst.Parsing.Model.ProductionElement;
import cn.jlu.edu.ccst.Parsing.Model.SNLProdcutionElement;
import cn.jlu.edu.ccst.WordsAnalyse.util.RegexUtil;

import java.util.HashMap;
import java.util.regex.Pattern;

public class ProductionElementController {
    HashMap<String, ProductionElement> elementHashMap;//提供非终极符、INTC、ID、分隔符等 到元素的映射，进而找到相应的产生式。

    public ProductionElementController() {
    }

    //Todo :从path中读取产生式并保存到hashmap中
    public ProductionElementController(String path) {

    }

    //TODO:实现从产生式字符串构造Production
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
            for(var element :right){
                var rightElement=getElement(element);
            }
            System.out.print("\n");
            //创建产生式类
            //将产生式关联到左部的元素中
            //返回结果
        }else{
            throw new RuntimeException("产生式"+productionString+"不正确");
        }
        return null;
    }

    public ProductionElement getElement(String content){
        if(!elementHashMap.containsKey(content)){
            elementHashMap.put(content,new SNLProdcutionElement(content));
        }
        return elementHashMap.get(content);
    }

}