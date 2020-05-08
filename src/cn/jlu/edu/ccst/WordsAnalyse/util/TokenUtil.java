package cn.jlu.edu.ccst.WordsAnalyse.util;

import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;

import java.util.ArrayList;

public class TokenUtil {
    public static Boolean isID(String s){
        if(RegexUtil.match("[a-zA-Z]([a-zA-Z]|[0-9])*", s)){
//            System.out.println(s);
            return true;
        }
        return false;
    }
    public static Boolean isINTC(String s){
        if(RegexUtil.match("[1-9]([0-9])*", s)){
            return true;
        }
        return false;
    }
    public static Boolean isReservedWord(String s){
        if(RegexUtil.match("program|type|var|integer|char|array|of|procedure|begin|while|do|if|then|else|fi|endwh|end|read|write|return", s)){
            return true;
        }
        return false;
    }





    public static  Boolean doToken(String s){
        InfoUtil.tokenList=new ArrayList<Token>();
        int row=1;
        int col=1;
        int col1=1;
        Boolean isInAnnotation=false;
        String s1;
        StringBuffer s_temp = new StringBuffer();
        Token t;

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='{'){
                isInAnnotation=true;
                t=new Token(row,col,"AnnotationHead","{");
                InfoUtil.tokenList.add(t);
            }
            if(s.charAt(i)=='}'&&isInAnnotation==true){
                if(isInAnnotation==true){
                    isInAnnotation=false;
                    t=new Token(row,col,"AnnotationHead","}");
                    InfoUtil.tokenList.add(t);
                }else{
                    System.out.println("error,row:"+row+"col:"+col);
                }
            }

            if(!isInAnnotation){

                if(s.charAt(i)=='\n'){
                    row++;
                    col=0;
                }
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
                            System.out.println("error,row:"+row+"col:"+col1);
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
                        t=new Token(row,col,"SingleSeperator",Character.toString(s.charAt(i)));
                        InfoUtil.tokenList.add(t);
                    }
                    col1=col+1;
                }

            }
            col++;





        }






        return true;
    }



}
