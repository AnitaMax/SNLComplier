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



}
