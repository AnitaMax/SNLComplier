package cn.jlu.edu.ccst.Parsing.Model;

import java.util.ArrayList;

public class AnalyseResult {
    boolean isSuccess=true;
    String failResult="";
    ArrayList<AnalyseLog> logs=new ArrayList<>();
    Tree tree;

    public AnalyseResult() {
        //初始化语法树
        Node root=new Node("Program");
        tree=new Tree(root);

    }


    @Override
    public String toString() {
        return "AnalyseResult{" +
                "isSuccess=" + isSuccess +
                ", failResult='" + failResult + '\'' +
                ", logs=\n," + logs +
                '}';
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getFailResult() {
        return failResult;
    }

    public void setFailResult(String failResult) {
        this.failResult = failResult;
    }

    public ArrayList<AnalyseLog> getLogs() {
        return logs;
    }

    public Tree getTree() {
        return tree;
    }
}
