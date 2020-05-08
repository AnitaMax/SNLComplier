package cn.jlu.edu.ccst.WordsAnalyse.Test.Util;

import cn.jlu.edu.ccst.WordsAnalyse.util.InfoUtil;
import cn.jlu.edu.ccst.WordsAnalyse.util.RegexUtil;
import cn.jlu.edu.ccst.WordsAnalyse.util.TokenUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenUtilTest {
    @Test
    void matchID(){

        assertTrue(TokenUtil.isID("a0123"));
        assertFalse(TokenUtil.isID("0123"));
    }

    @Test
    void iniRight(){
        InfoUtil.initialize();
        String s="program =p \n" +
                "type t = integer ; \n" +
                "var t v1; \n" +
                "\tchar v2; \n" +
                "begin \n" +
                "read(v1); \n" +
                "\tv1:=v1+10; \n" +
                "\twrite(v1) \n" +
                "end. ";
        TokenUtil.doToken(s);
        for(int i=0;i< InfoUtil.tokenList.size();i++){
            System.out.println(InfoUtil.tokenList.get(i).toString());

        }

    }

}