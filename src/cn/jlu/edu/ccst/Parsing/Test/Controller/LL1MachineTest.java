package cn.jlu.edu.ccst.Parsing.Test.Controller;

import cn.jlu.edu.ccst.Parsing.Controller.LL1Machine;
import cn.jlu.edu.ccst.Parsing.Model.SNLProdcutionElement;
import cn.jlu.edu.ccst.WordsAnalyse.util.InfoUtil;
import cn.jlu.edu.ccst.WordsAnalyse.util.TokenUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LL1MachineTest {
    @Test
    void TestParsing(){
        var lL1Machine=new LL1Machine();
        var result=lL1Machine.parsing(new ArrayList<>(),SNLProdcutionElement.getStartElement());
        System.out.println(result);
        assertFalse(result.isSuccess());

        var s="program p\n" +
                "        type\n" +
                "            t = integer;\n" +
                "            a=array[3..5] of integer;\n" +
                "            vv=record " +
                "                   integer v3,cd;" +
                "                end;\n" +
                "        var\n" +
                "            t v1;\n" +
                "            char v2;\n" +
                "            a v3;\n" +
                "        {\n" +
                "        测试注释\n" +
                "        }\n" +
                "        procedure test1(integer in1,in2);\n" +
                "        begin\n" +
                "        write(in1);\n"+
                "        in1:=in2\n" +
                "        end\n" +
                "\n" +
                "        begin\n" +
                "            v2:='1';\n" +
                "            v2:=vv.cd;\n" +
                "            read(v1);\n" +
                "            v1:=v1+10;\n" +
                "            write(v1)\n" +
                "        end" ;
        InfoUtil.initialize();
        TokenUtil.doToken(s);
        var tokens=InfoUtil.tokenList;
        var result2=lL1Machine.parsing(tokens,SNLProdcutionElement.getStartElement());
        System.out.println(result2);
        assertTrue(result2.isSuccess());
    }

}