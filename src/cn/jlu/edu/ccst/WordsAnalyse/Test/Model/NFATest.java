package cn.jlu.edu.ccst.WordsAnalyse.Test.Model;

import cn.jlu.edu.ccst.WordsAnalyse.Model.NFA;
import cn.jlu.edu.ccst.WordsAnalyse.Model.NFABuilderWithStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NFATest {


    @Test
    void createCharsetNFA() {
        assertTrue(NFA.createCharsetNFA("[a-z]").isMatch("x"));
    }

    @Test
    void acceptFromStart() {
        var nfa= NFABuilderWithStack.buildToNFA("[0-9]");
        assertEquals(nfa.acceptFromStart("1+1"),"1");
    }
}