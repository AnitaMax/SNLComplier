package cn.jlu.edu.ccst.WordsAnalyse.util;

import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;

import java.util.ArrayList;
import java.util.List;


public class InfoUtil {
    public static List<Token> tokenList;
    public static List<String> reservedWordList;
    public static List<String> seperatorList;



    public static void initialize(){
        reservedWordList=new ArrayList<String>();
        seperatorList=new ArrayList<String>();
        seperatorList.add("+");
        seperatorList.add("-");
        seperatorList.add("*");
        seperatorList.add("/");
        seperatorList.add("(");
        seperatorList.add(")");
        seperatorList.add("[");
        seperatorList.add("]");
        seperatorList.add(";");
        seperatorList.add(".");
        seperatorList.add("<");
        seperatorList.add("=");
        seperatorList.add(" ");
        seperatorList.add("EOF");
        seperatorList.add(":=");
        seperatorList.add("..");
        seperatorList.add(" ");
        seperatorList.add(":");



    }


}
