package cn.jlu.edu.ccst.WordsAnalyse.Model;

import cn.jlu.edu.ccst.WordsAnalyse.Model.Conditions.CharSetCondition;
import cn.jlu.edu.ccst.WordsAnalyse.Model.Conditions.CharacterCondition;
import cn.jlu.edu.ccst.WordsAnalyse.Model.Conditions.Condition;

import java.util.ArrayList;
import java.util.HashMap;


public class State {
    boolean isEnd;
    HashMap<Condition,State> transition;
    ArrayList<State> epsilonTransition;

    public State(boolean isEnd) {
        this.isEnd = isEnd;
        transition=new HashMap<>();
        epsilonTransition = new ArrayList<>();
    }

    public State addCharTransition(Character token, State to){
        this.transition.put(new CharacterCondition(token),to);
        return this;
    }

    public State addCharSetTransition(ArrayList<Character> charset,State to){
        this.transition.put(new CharSetCondition(charset),to);
        return this;
    }

    public State addEpsilonTransition(State to){
        this.epsilonTransition.add(to);
        return this;
    }
}
