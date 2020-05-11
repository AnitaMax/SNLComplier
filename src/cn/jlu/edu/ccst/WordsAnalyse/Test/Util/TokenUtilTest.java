package cn.jlu.edu.ccst.WordsAnalyse.Test.Util;

import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;
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


    @Test
    void getToken() {
        var s="program =p \n" +
                "type t = integer\n" +
                "var t v1;\n" +
                "char v2;\n" +
                "begin\n" +
                "read(v1);\n" +
                "v1:=v1+10;\n" +
                "write(v1)\n" +
                "end. ";
        System.out.println(TokenUtil.getToken(s));
    }
}


/*
测试程序
        program p
        type
            t = integer;
            a=array[3..5] of integer;
            vv=record integer v3,cd end;
        var
            t v1;
            char v2;
            a v3;
        {
        测试注释
        }
        procedure test1(integer in1,in2);
        begin
            in1=in2
        end

        begin
            v2:='1';
            v2:=vv.cd;
            read(v1);
            v1:=v1+10;
            write(v1)
        end

建议：
        1. 词法分析后空格的token不要了，因为太多了，不方便查看和处理
        2. 写的程序不用分号作为语句结束，分号是某些文法的
        3. 保留字大写，最好大小写的关键字都能正常识别
        4. record也是保留字
        5. char '1'
        6. 空行和注释行要算行数


 */