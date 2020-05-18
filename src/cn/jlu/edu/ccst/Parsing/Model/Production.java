package cn.jlu.edu.ccst.Parsing.Model;

import java.util.ArrayList;

public class Production {
    ProductionElement left;

    public ArrayList<ProductionElement> getRight() {
        return right;
    }

    ArrayList<ProductionElement> right;

    public ArrayList<ProductionElement> getPredict() {
        return predict;
    }

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


    public ProductionElement getLeft() {
        return left;
    }

    public void setLeft(ProductionElement left) {
        this.left = left;
    }



    public void setRight(ArrayList<ProductionElement> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        StringBuilder r= new StringBuilder();
        for (var s:right) {
            r.append(s.getContent());
            r.append(" ");
        }
        StringBuilder pre= new StringBuilder();
        for (var s:predict) {
            pre.append(s.getContent());
            pre.append(" ");
        }
        return "Production{" +left.getContent() +"->"+r+" predictSet:"+pre.toString()+"}";

    }
}
