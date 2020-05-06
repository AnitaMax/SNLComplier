package cn.jlu.edu.ccst.WordsAnalyse.Model.Conditions;

public class CharacterCondition implements Condition{
    char con;
    public CharacterCondition(char con) {
        this.con = con;
    }
    @Override
    public boolean accept(char in) {
        return in==con;
    }
}
