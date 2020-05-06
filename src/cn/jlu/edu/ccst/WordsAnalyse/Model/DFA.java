package cn.jlu.edu.ccst.WordsAnalyse.Model;

import java.util.ArrayList;

public class DFA {

    /* TODO:
        （低优先级）实现NFA->DFA以及DFA化简
        难点：如果从ASCII便历就失去了中文等字符集的通用性。
     */
    public static DFA buildToDFA(NFA nfa){
        ArrayList<State> currentState=NFA.getClosure(nfa.startState);


//        for(char token:exp.toCharArray()){
//            var nextStates=new ArrayList<State>();
//            currentState.forEach(state-> {
//                for(var condition:state.transition.keySet()){
//                    if(condition.accept(token)){
//                        nextStates.addAll(NFA.getClosure(state.transition.get(condition)));
//                    }
//                }
//            });
//            currentState=nextStates;
//        }
        return null;
    }
}
