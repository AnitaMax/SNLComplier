package cn.jlu.edu.ccst.WordsAnalyse.Test.Model;

import cn.jlu.edu.ccst.WordsAnalyse.Model.NFABuilderWithStack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NFABuilderWithStackTest {
    @Test
    void insertExplicitConcatOperator() {
        assertEquals("(a|b)*·a·b·b", NFABuilderWithStack.insertExplicitConcatOperator("(a|b)*abb"));
    }

    @Test
    void infixToPostfix() {
        assertEquals("ab|*c·",NFABuilderWithStack.infixToPostfix("(a|b)*·c"));
    }
}