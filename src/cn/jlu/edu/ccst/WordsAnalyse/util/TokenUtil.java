package cn.jlu.edu.ccst.WordsAnalyse.util;

import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;

import java.util.ArrayList;

public class TokenUtil {
    public static Boolean isID(String s){
        //            System.out.println(s);
        return RegexUtil.match("[a-zA-Z]([a-zA-Z0-9])*", s);
    }
    public static Boolean isINTC(String s){
        return RegexUtil.match("[1-9]([0-9])*", s);
    }
    public static Boolean isReservedWord(String s){
        return RegexUtil.match("program|PROGRAM|type|TYPE|var|VAR|integer|INTEGER|char|CHAR|array|Array|of|OF|procedure|PROCEDURE|begin|BEGIN|while|WHILE|do|DO|if|IF|then|THEN|else|ELSE|fi|FI|endwh|ENDWH|end|END|read|READ|write|WRITE|return|RETURN|record|RECORD", s);
    }





    public static  Boolean doToken(String ss){
        InfoUtil.tokenList= new ArrayList<>();
        int row=1;
        int col=1;
        int col1=1;
        boolean isInAnnotation=false;
        String s1;
        StringBuffer s_temp = new StringBuffer();
        StringBuffer s = new StringBuffer();
        s.append(ss);
        s.append(' ');
        Token t;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='{'){
                isInAnnotation=true;
//                t=new Token(row,col,"AnnotationHead","{");
//                InfoUtil.tokenList.add(t);
            }
            if(s.charAt(i)=='}'){
                if(isInAnnotation){
                    isInAnnotation=false;
//                    t=new Token(row,col,"AnnotationTail","}");
//                    InfoUtil.tokenList.add(t);
                    continue;
                }else{
                    System.out.println("error,row:"+row+"col:"+col);
                }
            }

            if(!isInAnnotation){


                if(s.charAt(i)!='\n'&&s.charAt(i)!='\t'&&!InfoUtil.seperatorList.contains(String.valueOf(s.charAt(i)))){
                    s_temp.append(s.charAt(i));
                }else{

                    if(s_temp.length()!=0){
                        s1=s_temp.toString();
                        s_temp = new StringBuffer();
                        if(isID(s1)){
                            if(isReservedWord(s1)){
                                t=new Token(row,col1,"ReservedWord",s1);
                                InfoUtil.tokenList.add(t);
                            }else{
                                t=new Token(row,col1,"ID",s1);
                                InfoUtil.tokenList.add(t);
                            }
                        } else if(isINTC(s1)){
                            t=new Token(row,col1,"INTC",s1);
                            InfoUtil.tokenList.add(t);
                        }else {
                            if(s1.charAt(0)=='\''){
                                int intc=(int)s1.charAt(1);
                                t=new Token(row,col,"INTC",String.valueOf(intc));
                                InfoUtil.tokenList.add(t);
                            }else{
                                System.out.println("error,row:"+row+"col:"+col1);
                            }

                        }
                    }
                    if(s.charAt(i)!='\n'){
                        if(s.charAt(i)==':'){
                            if(s.charAt(++i)=='='){
                                t=new Token(row,col,"DoubleSeperator",":=");
                                InfoUtil.tokenList.add(t);
                                col1=col+2;
                                col=col+2;
                                continue;
                            }
                            else{
                                System.out.println("error,row:"+row+"col:"+col);
                                return false;
                            }
                        }
                        if(s.charAt(i)=='.'){
                            if(s.charAt(++i)=='.'){
                                t=new Token(row,col,"DoubleSeperator","..");
                                InfoUtil.tokenList.add(t);
                                col1=col+1;
                                col=col+1;
                                continue;
                            }else{
                                t=new Token(row,col,"SingleSeperator",".");
                                InfoUtil.tokenList.add(t);
                                i--;
                                col1=col+1;
                                col=col+1;
                                continue;
                            }


                        }
                        if(s.charAt(i)!=' '){
                            t=new Token(row,col,"SingleSeperator",Character.toString(s.charAt(i)));
                            InfoUtil.tokenList.add(t);
                        }

                    }
                    col1=col+1;
                }

            }
            col++;
            if(s.charAt(i)=='\n'){
                row++;
                col=0;
            }





        }
        //增加结束token
        t=new Token(row,col1,"SingleSeperator","$");
        InfoUtil.tokenList.add(t);






        return true;
    }
    public static String getToken(String s){
        InfoUtil.initialize();
        TokenUtil.doToken(s);
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i< InfoUtil.tokenList.size();i++){
            stringBuilder.append(InfoUtil.tokenList.get(i).toString());
        }
        return stringBuilder.toString();
    }

}
