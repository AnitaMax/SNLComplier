package cn.jlu.edu.ccst.WordsAnalyse.Test.Util;

import cn.jlu.edu.ccst.WordsAnalyse.Model.NFABuilderWithStack;
import cn.jlu.edu.ccst.WordsAnalyse.util.RegexUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexUtilTest {

    @Test
    void matchConCat() {
        System.out.println("Test concat works as expect");
        assertTrue(RegexUtil.match("a", "a"));
        assertFalse(RegexUtil.match("a", "b"));
        assertTrue(RegexUtil.match("ab", "ab"));
        assertFalse(RegexUtil.match("ab", "b"));
        assertTrue(RegexUtil.match("成*立", "成成成成成立"));
    }

    @Test
    void matchClosure(){
        System.out.println("Test closure works as expect");
        assertTrue(RegexUtil.match("a*", "a"));
        assertTrue(RegexUtil.match("a*", ""));
        assertTrue(RegexUtil.match("a*", "aaaaa"));
        assertFalse(RegexUtil.match("a*", "b"));
        assertTrue(RegexUtil.match("a*b", "ab"));
        assertTrue(RegexUtil.match("a*b", "aaaaab"));
        assertTrue(RegexUtil.match("a(abc)*c", "ac"));
        assertTrue(RegexUtil.match("a(abc)*c", "aabcc"));
        assertTrue(RegexUtil.match("a(abc)*c", "aabcabcc"));
        assertFalse(RegexUtil.match("a(abc)*c", "addc"));
    }

    @Test
    void matchUnion(){
        System.out.println("Test union works as expect");
        assertTrue(RegexUtil.match("a|b", "a"));
        assertTrue(RegexUtil.match("a|b", "b"));
        assertFalse(RegexUtil.match("a|b", "c"));
        assertFalse(RegexUtil.match("a|b", "ab"));
        assertTrue(RegexUtil.match("jack|rose", "jack"));
        assertTrue(RegexUtil.match("jack|rose", "rose"));
        assertFalse(RegexUtil.match("jack|rose", "jac"));
    }

    @Test
    void matchZeroToOne(){
        System.out.println("Test zero-to-one works as expect");
        assertTrue(RegexUtil.match("a?", "a"));
        assertTrue(RegexUtil.match("a?", ""));
        assertFalse(RegexUtil.match("a?", "b"));
        assertTrue(RegexUtil.match("ab?c", "ac"));
        assertTrue(RegexUtil.match("ab?c", "abc"));
        assertTrue(RegexUtil.match("a(abc)?c", "ac"));
        assertTrue(RegexUtil.match("a(abc)?c", "aabcc"));
        assertFalse(RegexUtil.match("a(abc)?c", "aabcabcc"));
        assertFalse(RegexUtil.match("a(abc)?c", "addc"));
        assertFalse(RegexUtil.match("ab?c", "abbbbc"));
    }

    @Test
    void matchOneToMore(){
        System.out.println("Test one-to-more works as expect");
        assertTrue(RegexUtil.match("a+", "a"));
        assertFalse(RegexUtil.match("a+", ""));
        assertFalse(RegexUtil.match("a+", "b"));
        assertFalse(RegexUtil.match("ab+c", "ac"));
        assertTrue(RegexUtil.match("ab+c", "abc"));
        assertTrue(RegexUtil.match("ab+c", "abbbbc"));
        assertFalse(RegexUtil.match("a(abc)+c", "ac"));
        assertTrue(RegexUtil.match("a(abc)+c", "aabcc"));
        assertTrue(RegexUtil.match("a(abc)+c", "aabcabcc"));
        assertFalse(RegexUtil.match("a(abc)+c", "addc"));

    }

    @Test
    void matchComplicated (){
        System.out.println("Test complicated exp works as expect");
        assertTrue(RegexUtil.match("(jack|rose)*", "jackjackrose"));
        assertFalse(RegexUtil.match("a(bc)*", "ab"));
        assertTrue(RegexUtil.match("a(bc)*", "abc"));
        assertTrue(RegexUtil.match("a(b|c)*", "a"));
        assertTrue(RegexUtil.match("a(b|c)*", "abbccb"));
    }

    @Test
    void matchCharSet (){
        System.out.println("Test charset exp works as expect");
        assertTrue(RegexUtil.match("[a-z]", "x"));
        assertTrue(RegexUtil.match("[a-zA-Z]", "B"));
        assertTrue(RegexUtil.match("[a-zA-Z]*b", "Bb"));
        assertTrue(RegexUtil.match("[a-zA-Z]*b", "BBBBBb"));
        assertTrue(RegexUtil.match("D[a-zA-Z]*b", "DBBBBBb"));
        assertTrue(RegexUtil.match("D[0-9]?b", "Db"));
        assertFalse(RegexUtil.match("D[0-9]?b", "D66b"));
        assertFalse(RegexUtil.match("[0-9]", ""));
        assertFalse(RegexUtil.match("[0-9]+", "dd"));
        assertTrue(RegexUtil.match("[0-9]+", "9909"));
        assertTrue(RegexUtil.match("[a-zA-Z]([a-zA-Z]|[0-9])*", "dd9909gg"));
        assertFalse(RegexUtil.match("[a-zA-Z]([a-zA-Z]|[0-9])", "9909gg"));
    }
}