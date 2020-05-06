package cn.jlu.edu.ccst.WordsAnalyse.Test;

import cn.jlu.edu.ccst.WordsAnalyse.util.RegexUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexUtilTest {

    @Test
    void insertExplicitConcatOperator() {
        assertEquals("(a|b)*·a·b·b", RegexUtil.insertExplicitConcatOperator("(a|b)*abb"));
    }

    @Test
    void infixToPostfix() {
        assertEquals("ab|*c·",RegexUtil.infixToPostfix("(a|b)*·c"));
    }

    @Test
    void matchConCat() {
        System.out.println("Test concat works as expect");
        assertTrue(RegexUtil.match("a", "a"));
        assertFalse(RegexUtil.match("a", "b"));
        assertTrue(RegexUtil.match("ab", "ab"));
        assertFalse(RegexUtil.match("ab", "b"));

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
}