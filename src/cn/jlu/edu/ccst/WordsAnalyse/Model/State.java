package cn.jlu.edu.ccst.WordsAnalyse.Model;

import java.util.ArrayList;
import java.util.HashMap;


public class State {
    boolean isEnd;
    HashMap<Character,State> transition;
    ArrayList<State> epsilonTransition;

    public State(boolean isEnd) {
        this.isEnd = isEnd;
        transition=new HashMap<>();
        epsilonTransition = new ArrayList<>();
    }

    public State addTransition(Character token, State to){
        this.transition.put(token,to);
        return this;
    }

    public State addEpsilonTransition(State to){
        this.epsilonTransition.add(to);
        return this;
    }
}
