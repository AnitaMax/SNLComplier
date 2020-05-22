package cn.jlu.edu.ccst.View.Util;

public class CodeUtil {
    public static String DefaultCode="program p\n" +
            "type\n" +
            "    t = integer;\n" +
            "    a=array[3..5] of integer;\n" +
            "    vv=record \n" +
            "        integer v3,cd;\n" +
            "     end;\n" +
            "var\n" +
            "    t v1;\n" +
            "    char v2;\n" +
            "    a v3;\n" +
            "{\n" +
            "测试注释\n" +
            "}\n" +
            "procedure test1(integer in1,in2);\n" +
            "begin\n" +
            "    in1:=in2\n" +
            "end\n" +
            "\n" +
            "begin\n" +
            "    if i<2 then \n" +
            "        v2:=3\n" +
            "    else\n" +
            "        v2:=4\n" +
            "    fi;\n" +
            "    while i<3 do\n" +
            "        v2:=v2+1\n" +
            "    endwh;\n" +
            "    v2:='1';\n" +
            "    v2:=vv.cd;\n" +
            "    read(v1);\n" +
            "    v1:=v1+10;\n" +
            "    write(v1)\n" +
            "end";
}
