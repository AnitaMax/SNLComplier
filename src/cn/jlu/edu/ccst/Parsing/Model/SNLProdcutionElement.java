package cn.jlu.edu.ccst.Parsing.Model;

import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;

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

    public boolean accept(Token token)  {
        if(isEnd){
            switch (token.getType()){
                case "DoubleSeperator":
                case "ReservedWord":
                case "SingleSeperator":
                    if(isFixed&& token.getValue().equals(Content))
                        return true;
                    break;
                case "ID":
                case "INTC":
                    if(!isFixed&& token.getType().equals(Content))
                        return true;
                    break;
                default:
                    throw new RuntimeException("未识别的Token类型");
            }
        }
        return false;
    }
}
