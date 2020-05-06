package cn.jlu.edu.ccst.WordsAnalyse.Model;

import cn.jlu.edu.ccst.WordsAnalyse.Model.Conditions.CharSetCondition;
import cn.jlu.edu.ccst.WordsAnalyse.util.RegexUtil;

import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

public class NFA {
    State startState;
    State endState;

    public NFA(State startState, State endState) {
        this.startState = startState;
        this.endState = endState;
    }


    /**
     *获得状态的s的ε-closure(s)
     * @param state 需要求闭包的state
     * @return ε-closure(state)
     */
    static ArrayList<State> getClosure(State state){
        var visited =new ArrayList<State>();
        var stack =new Stack<State>();
        visited.add(state);
        stack.push(state);

        while (!stack.isEmpty()){
            var aState=stack.pop();
            var nextStates=aState.epsilonTransition.stream().filter(next->!visited.contains(next)).collect(Collectors.toList());
            visited.addAll(nextStates);
            stack.addAll(nextStates);
        }
        return visited;
    }
    static ArrayList<State> move(State state,Character character){
        var nextStates=new ArrayList<State>();
        for(var condition:state.transition.keySet()){
            if(condition.accept(character)){
                nextStates.addAll(NFA.getClosure(state.transition.get(condition)));
            }
        }
        return nextStates;
    }
    public boolean isMatch(String exp){
        ArrayList<State> currentState=NFA.getClosure(startState);
        for(char token:exp.toCharArray()){
            var nextStates=new ArrayList<State>();
            currentState.forEach(state-> nextStates.addAll(move(state,token)));
            currentState=nextStates;
        }
        return currentState.stream().anyMatch(state->state.isEnd);
    }

    static NFA createBasicNFA(Character token){
        var startState=new State(false);
        var endState=new State(true);
        if(token!=null){
            startState.addCharTransition(token,endState);
        }else {
            startState.addEpsilonTransition(endState);
        }
        return new NFA(startState,endState);
    }
    public  static NFA createCharsetNFA(String token){
        var startState=new State(false);
        var endState=new State(true);
        if(token!=null){
            var groupNum=(token.length()-2)/3;
            var charset=new ArrayList<Character>();
            for(int i=1;i<=groupNum;i++){
                charset.addAll(CharSetCondition.getSet(token.charAt(3*i-2),token.charAt(3*i)));
            }
            startState.addCharSetTransition(charset,endState);
        }else {
            startState.addEpsilonTransition(endState);
        }
        return new NFA(startState,endState);
    }

    static NFA union(NFA aNFA,NFA bNFA){
        var newStartState=new State(false);
        var newEndState=new State(true);

        newStartState.addEpsilonTransition(aNFA.startState)
                .addEpsilonTransition(bNFA.startState);
        aNFA.endState.addEpsilonTransition(newEndState).isEnd = false;
        bNFA.endState.addEpsilonTransition(newEndState).isEnd = false;

        return new NFA(newStartState, newEndState);
    }
    static NFA  concat(NFA aNFA, NFA bNFA) {
        var newStartState = aNFA.startState;
        var newEndState = bNFA.endState;

        aNFA.endState.addEpsilonTransition(bNFA.startState).isEnd = false;

        return new NFA(newStartState, newEndState);
    }

    static NFA closure( NFA nfa) {
//        var newStartState = new State(false);
//        var newEndState = new State(true);
//
//        newStartState.addEpsilonTransition(nfa.startState)
//                .addEpsilonTransition(newEndState);
//        nfa.endState.addEpsilonTransition(nfa.startState)
//                .addEpsilonTransition(newEndState)
//                .isEnd = false;
//
//        return new NFA(newStartState, newEndState);
        nfa.startState.addEpsilonTransition(nfa.endState);
        nfa.endState.addEpsilonTransition(nfa.startState);
        return nfa;
    }

    static NFA zeroOrOne(NFA nfa) {
        var newStartState = new State(false);
        var newEndState = new State(true);

        newStartState.addEpsilonTransition(nfa.startState)
                .addEpsilonTransition(newEndState);
        nfa.endState.addEpsilonTransition(newEndState)
                .isEnd = false;

        return new NFA(newStartState, newEndState);
    }

    static NFA oneOrMore(NFA nfa) {
//        var newStartState = new State(false);
//        var newEndState = new State(true);
//
//        newStartState.addEpsilonTransition(nfa.startState);
//        nfa.endState.addEpsilonTransition(nfa.startState)
//                .addEpsilonTransition(newEndState)
//                .isEnd = false;
//
//        return new NFA(newStartState, newEndState);
        nfa.endState.addEpsilonTransition(nfa.startState);
        return nfa;
    }
}
