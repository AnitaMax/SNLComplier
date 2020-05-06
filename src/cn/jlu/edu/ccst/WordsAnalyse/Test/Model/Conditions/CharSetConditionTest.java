package cn.jlu.edu.ccst.WordsAnalyse.Test.Model.Conditions;

import cn.jlu.edu.ccst.WordsAnalyse.Model.Conditions.CharSetCondition;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CharSetConditionTest {

    @Test
    void addSet() {
        var set = new ArrayList<>(CharSetCondition.getSet('a', 'z'));
        CharSetCondition charSetCondition = new CharSetCondition(set);
        assertTrue(charSetCondition.accept('x'));
        assertFalse(charSetCondition.accept('0'));
    }

    @Test
    void accept() {
        var set = new ArrayList<>(CharSetCondition.getSet('0', '9'));
        CharSetCondition charSetCondition = new CharSetCondition(set);
        assertTrue(charSetCondition.accept('5'));
        assertFalse(charSetCondition.accept('A'));
    }
}