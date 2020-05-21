package cn.jlu.edu.ccst.Parsing.Test.Controller;

import cn.jlu.edu.ccst.Parsing.Controller.ProductionElementController;
import cn.jlu.edu.ccst.Parsing.Model.ProductionElement;
import cn.jlu.edu.ccst.Parsing.Util.FileReaderUtil;
import cn.jlu.edu.ccst.WordsAnalyse.util.RegexUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductionElementControllerTest {

    @Test
    void JudgeProduction()  {
        var lines=FileReaderUtil.readFile("../productions.txt");
        String left="";
        String[] right;
        for (int row=0;row<lines.size();row++) {
            var line=lines.get(row);
            //去掉前括号
            var place=line.indexOf(')');
            assertTrue(place>0);
            line=line.substring(place+1);
            //去掉左右空格
            line=line.trim();
            //划分左右
            if(line.startsWith("|")){
                var s=line.replace('|',' ');
                right=s.split(" ");
            }else{
                var elements=line.split("::=");
                left=elements[0].trim();
                right=elements[1].split(" ");
            }
            for(int i=0;i<right.length;i++){
                right[i]=right[i].trim();
            }
            //处理左部
            System.out.print(left+"->");
            //处理右部
            for(var element :right){
                if(element.equals(" ") || element.equals(""))
                    continue;
                if(!RegexUtil.match("[A-Za-z]+",element)&&!Pattern.matches("[+\\-/()\\[\\];=,<>*.]|..|:=",element)){
                    System.out.println(row+" "+element+" ");
                }
                System.out.print(element+" ");
            }
            System.out.print("\n");
        }

    }
    @Test
    void JudgeProductionLines()  {
        var lines=FileReaderUtil.readFile("../productionLines.txt");
        for (int row=0;row<lines.size();row++) {
            var line=lines.get(row);
            //去掉左右空格
            line=line.trim();
            //划分左右
            var elements=line.split("->");
            String left=elements[0];
            String[] right=elements[1].split(" ");
            //处理左部
            System.out.print(left+"->");
            //处理右部
            for(var element :right){
                if(element.equals(" ") || element.equals(""))
                    continue;
                if(!RegexUtil.match("[A-Za-z]+",element)&&!Pattern.matches("[+\\-/()\\[\\];=,<>*.]|..|:=",element)){
                    System.out.println("error: "+row+" "+element+" ");
                }
                System.out.print(element+" ");
            }
            System.out.print("\n");
        }

    }

    @Test
    void getElement() {
        var con=new ProductionElementController();
        var ele1=con.getSNLElement("INTC");
        assertTrue(ele1.isEnd());
        assertFalse(ele1.isFixed());

        var ele2=con.getSNLElement(";");
        assertTrue(ele2.isEnd());
        assertTrue(ele2.isFixed());

        var ele3=con.getSNLElement("Pro");
        assertFalse(ele3.isEnd());

        var ele4=con.getSNLElement("INTC");
        assertTrue(ele4.isEnd());
        assertFalse(ele4.isFixed());

    }
    @Test
    void testFirstSet(){
        var con=new ProductionElementController("aaa");
        con.iniFirstSet();
        con.printSet();
    }
    @Test
    void aaa(){
        ArrayList<ProductionElement> curFollowSet=new ArrayList<ProductionElement>();



    }
    @Test
    void testFollowSet(){
        var con=new ProductionElementController("aaa");
        con.iniFirstSet();
        con.iniFollowSet();
        con.setProductionPredict();
        con.printProductions();
        con.printSet();


    }

    @Test
    void readProductionsFromFile(){
        var con=new ProductionElementController("../productionLines.txt");
        con.printProductions();

    }
}