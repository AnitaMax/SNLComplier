package cn.jlu.edu.ccst.Parsing.Model;

import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;

import java.util.Stack;

public class AnalyseLog{
    String stack;
    String input;
    String action;

    public AnalyseLog (Token token, Stack<ProductionElement> analysingStack, String result){
        //记录过程
        var stackStatus=new StringBuilder();
        analysingStack.forEach(ele-> stackStatus.insert(0,ele.getContent()+" "));
        stack=stackStatus.toString();
        if(token!=null)
            input="line: "+token.getRow()+" "+token.getType()+" "+token.getValue();
        else
            input=" ";
        action=result;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "AnalyseLog{" +
                "stack='" + stack + '\'' +
                ", input='" + input + '\'' +
                ", action='" + action + '\'' +
                "}\n";
    }
}
