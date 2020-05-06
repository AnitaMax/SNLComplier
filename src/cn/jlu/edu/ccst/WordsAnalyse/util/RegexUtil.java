package cn.jlu.edu.ccst.WordsAnalyse.util;

import cn.jlu.edu.ccst.WordsAnalyse.Model.NFA;
import cn.jlu.edu.ccst.WordsAnalyse.Model.NFABuilderWithStack;

import java.util.HashMap;
import java.util.Stack;

public class RegexUtil {

    public static boolean match(String regex,String exp){
        var nfa= NFABuilderWithStack.buildToNFA(regex);
        return nfa.isMatch(exp);
    }
}
