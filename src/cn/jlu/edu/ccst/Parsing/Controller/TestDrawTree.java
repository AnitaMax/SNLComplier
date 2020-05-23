package cn.jlu.edu.ccst.Parsing.Controller;

import cn.jlu.edu.ccst.Parsing.Model.SNLProdcutionElement;
import cn.jlu.edu.ccst.WordsAnalyse.util.InfoUtil;
import cn.jlu.edu.ccst.WordsAnalyse.util.TokenUtil;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestDrawTree extends JFrame{
    private HashMap<Integer, Integer> layerHashMap;

    public TestDrawTree(){
        super("Test Draw Tree");

        this.setLayout(null);
        this.setBounds(0, 0, 900, 800);

        TreePanel panel = new TreePanel(TreePanel.CHILD_ALIGN_RELATIVE);
        Node root=getRoot();
        panel.setLayerHashMap(layerHashMap);


        panel.setTree(root);
        panel.setGridColor(Color.gray);
        panel.setBackground(Color.white);
        panel.setLinkLineColor(Color.black);
        panel.setStringColor(Color.BLACK);


        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setBounds(0, 0, 1520, 800);
/**
 * 要加滚动条就要让panel的宽高大于scrollPane的宽高..你只要上下的..只要高大于就行了..
 */
        panel.setPreferredSize(new Dimension(scrollPane.getWidth()*3, scrollPane.getHeight()*3));
        this.add(scrollPane);
        panel.revalidate();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }


    public static void main(String[] args){
        new TestDrawTree();

    }

    public Node getRoot(){
        //        Node n = new Node("root");
//        Node a1 = new Node("a1");
//        Node a2 = new Node("a2");
//        n.add(a1);
//        n.add(a2);
//
//        Node a3 = new Node("a3");
//        Node b1 = new Node("b1");
//        Node d1 = new Node("d1");
//        Node d2 = new Node("d2");
//        b1.add(d1);
//        b1.add(d2);
//        a3.add(b1);
//
//        a3.add(new Node("b2"));
//        a3.add(new Node("b3"));
//        Node n3 = new Node("b4");
//        a3.add(n3);
//        n3.add(new Node("c1"));
//        n3.add(new Node("c2"));
//
//        n.add(a3);
        var s="program p\n" +
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
                "end" ;
        var lL1Machine=new LL1Machine();
        InfoUtil.initialize();
        TokenUtil.doToken(s);
        var tokens=InfoUtil.tokenList;
        var result2=lL1Machine.parsing(tokens, SNLProdcutionElement.getStartElement());
//        System.out.println(result2);
        Node root=lL1Machine.getTree().getRoot();
        layerHashMap=lL1Machine.tree.getChildNumOfLayer();

        root.printAllNode(root);

        return root;

    }
}