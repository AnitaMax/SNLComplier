package cn.jlu.edu.ccst.WordsAnalyse.Model.Conditions;

public class AnyCharCondition implements Condition {

    @Override
    public boolean accept(char in) {
        return true;
    }
}
