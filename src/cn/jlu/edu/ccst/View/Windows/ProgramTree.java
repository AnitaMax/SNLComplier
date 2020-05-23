package cn.jlu.edu.ccst.View.Windows;

import cn.jlu.edu.ccst.Parsing.Controller.LL1Machine;
import cn.jlu.edu.ccst.Parsing.Controller.Node;
import cn.jlu.edu.ccst.Parsing.Model.SNLProdcutionElement;
import cn.jlu.edu.ccst.View.Component.TreePanel;
import cn.jlu.edu.ccst.View.Util.CodeUtil;
import cn.jlu.edu.ccst.WordsAnalyse.util.InfoUtil;
import cn.jlu.edu.ccst.WordsAnalyse.util.TokenUtil;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class ProgramTree extends JFrame {
    private HashMap<Integer, Integer> layerHashMap;

    public ProgramTree() {
        super("语法树");

        this.setLayout(new BorderLayout());
        this.setBounds(0, 0, 900, 800);

        TreePanel panel = new TreePanel(TreePanel.CHILD_ALIGN_RELATIVE);
        Node root = getRoot();
        panel.setLayerHashMap(layerHashMap);


        panel.setTree(root);
        panel.setGridColor(Color.gray);
        panel.setBackground(Color.white);
        panel.setLinkLineColor(Color.black);
        panel.setStringColor(Color.BLACK);


        JScrollPane scrollPane = new JScrollPane(panel);

        scrollPane.setBounds(0, 0, 1520, 800);

        //要加滚动条就要让panel的宽高大于scrollPane的宽高..你只要上下的..只要高大于就行了..
        panel.setPreferredSize(new Dimension(scrollPane.getWidth() * 3, scrollPane.getHeight() * 3));
        this.add(scrollPane, BorderLayout.CENTER);
        panel.revalidate();

        //设置焦点位置
        scrollPane.getViewport().setViewPosition(new Point(scrollPane.getWidth(), 0));


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 1200, 600);


    }

    public Node getRoot() {
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
        var s = CodeUtil.DefaultCode;
        var lL1Machine = new LL1Machine();
        InfoUtil.initialize();
        TokenUtil.doToken(s);
        var tokens = InfoUtil.tokenList;
        var result2 = lL1Machine.parsing(tokens, SNLProdcutionElement.getStartElement());
//        System.out.println(result2);
        Node root = lL1Machine.getTree().getRoot();
        layerHashMap = lL1Machine.getTree().getChildNumOfLayer();

        root.printAllNode(root);

        return root;

    }
}