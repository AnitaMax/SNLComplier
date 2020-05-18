package cn.jlu.edu.ccst.Parsing.Model;

import java.util.ArrayList;

public class AnalyseResult {
    boolean isSuccess=true;
    String failResult="";


    public ArrayList<AnalyseLog> getLogs() {
        return logs;
    }

    public void setLogs(ArrayList<AnalyseLog> logs) {
        this.logs = logs;
    }

    ArrayList<AnalyseLog> logs=new ArrayList<>();


    public AnalyseResult() {
    }

    public AnalyseResult(boolean isSuccess, String failResult) {
        this.isSuccess = isSuccess;
        this.failResult = failResult;
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
}
