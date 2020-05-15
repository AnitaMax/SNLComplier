package cn.jlu.edu.ccst.Parsing.Model;

import java.util.regex.Pattern;

public class SNLProdcutionElement extends ProductionElement{
    public SNLProdcutionElement(boolean isEnd, boolean isFixed, String content) {
        super(isEnd, isFixed, content);
    }
    public SNLProdcutionElement(String content) {
        super();
        this.Content=content;
        if(content.equals(content.toUpperCase())|| Pattern.matches("[+\\-/()\\[\\];=,<>*.]|..|:=",content)){
            //终结符
            this.isEnd=true;
            this.isFixed= !content.equals("INTC") && !content.equals("ID");
        }else{
            //非终结符
            this.isEnd=false;
        }
    }
}
