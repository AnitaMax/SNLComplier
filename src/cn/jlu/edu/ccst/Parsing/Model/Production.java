package cn.jlu.edu.ccst.Parsing.Model;

import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;

import java.util.ArrayList;

public class Production {
    ProductionElement left;

    public ArrayList<ProductionElement> getRight() {
        return right;
    }

    ArrayList<ProductionElement> right;
    ArrayList<ProductionElement> predict;

    public Production(ProductionElement left, ArrayList<ProductionElement> right) {
        this.left = left;
        this.right = right;
    }

    public Production() {
    }

    public void setPredict(ArrayList<ProductionElement> predict) {
        this.predict = predict;
    }
}
