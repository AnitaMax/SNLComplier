package cn.jlu.edu.ccst.Parsing.Model;

public class AnalyseLog{
    String stack;
    String input;
    String action;

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
