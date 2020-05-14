package cn.jlu.edu.ccst.Parsing.Model;

import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;

import java.util.ArrayList;

public class Production {
    ProductionElement left;
    ArrayList<ProductionElement> right;
    ArrayList<ProductionElement> predict;

    public Production(ProductionElement left, ArrayList<ProductionElement> right) {
        this.left = left;
        this.right = right;
    }

    public Production() {
    }

//TODO:实现从产生式字符串构造Production
    /**
     * 从产生式字符串构造Production
     * 注意：表示空串的“yifusiluo”艾普西龙 写作 EPSILON
     * 产生式中大写单词表示终极符，只有首字母大写的是非终极符
     * @param productionString 产生式字符串 例如	 ProcName	 ::=	ID
     */
    public Production(String productionString) {

    }

}
