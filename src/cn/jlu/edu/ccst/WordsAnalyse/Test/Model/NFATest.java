package cn.jlu.edu.ccst.WordsAnalyse.Test.Model;

import cn.jlu.edu.ccst.WordsAnalyse.Model.NFA;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NFATest {

    @Test
    void createCharsetNFA() {
        assertTrue(NFA.createCharsetNFA("[a-z]").isMatch("x"));
    }
}