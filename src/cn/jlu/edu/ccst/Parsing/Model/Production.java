package cn.jlu.edu.ccst.Parsing.Model;

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

    public void setPredict(ArrayList<ProductionElement> predict) {
        this.predict = predict;
    }

    @Override
    public String toString() {
        StringBuilder r= new StringBuilder();
        for (var s:right) {
            r.append(s.getContent());
            r.append(" ");
        }
        return "Production{" +left.getContent() +"->"+r+"}";
    }
}
