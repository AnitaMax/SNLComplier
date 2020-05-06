package cn.jlu.edu.ccst.WordsAnalyse.Model.Conditions;

import java.util.ArrayList;

public class CharSetCondition implements Condition {
    ArrayList<Character> characters;

    public CharSetCondition(ArrayList<Character> characters) {
        this.characters = characters;
    }

    public static ArrayList<Character> getSet(char from,char to){
        var chars=new ArrayList<Character>();
        for(int i=from;i<=to;i++){
            chars.add((char)i);
        }
        return chars;
    }

//    public CharSetCondition addSet(ArrayList<Character> chars){
//        this.characters.addAll(chars);
//        return this;
//    }
    @Override
    public boolean accept(char in) {
        return characters.contains(in);
    }
}
