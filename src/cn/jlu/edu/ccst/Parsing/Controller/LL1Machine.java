package cn.jlu.edu.ccst.Parsing.Controller;

import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;

import java.util.List;

public class LL1Machine {
    ProductionElementController productionElementController;

    public LL1Machine() {
        productionElementController=new ProductionElementController("../productionLines.txt");
    }

    public void parsing(List<Token> tokens){

    }
}
