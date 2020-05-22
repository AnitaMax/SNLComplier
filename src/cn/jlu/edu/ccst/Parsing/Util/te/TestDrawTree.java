package cn.jlu.edu.ccst.Parsing.Util.te;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * @author John
 *
 */
public class TestDrawTree extends JFrame{

    public TestDrawTree(){
        super("Test Draw Tree");
        initComponents();
    }

    public static void main(String[] args){
        TestDrawTree frame = new TestDrawTree();

        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initComponents(){
        /*
         * 初始化树的数据
         */
        Node n = new Node("root");
        Node a1 = new Node("a1");
        Node a2 = new Node("a2");
        n.add(a1);
        n.add(a2);

        Node a3 = new Node("a3");
        Node b1 = new Node("b1");
        Node d1 = new Node("d1");
        Node d2 = new Node("d2");
        b1.add(d1);
        b1.add(d2);
        a3.add(b1);

        a3.add(new Node("b2"));
        a3.add(new Node("b3"));
        Node n3 = new Node("b4");
        a3.add(n3);
        n3.add(new Node("c1"));
        n3.add(new Node("c2"));

        n.add(a3);
        //n.printAllNode(n);	//输出树

        /*
         * 创建一个用于绘制树的面板并将树传入,使用相对对齐方式
         */
        TreePanel panel1 = new TreePanel(TreePanel.CHILD_ALIGN_RELATIVE);
        panel1.setTree(n);
        panel1.setBackground(Color.white);
        panel1.setGridColor(Color.gray);
        panel1.setStringColor(Color.BLACK);

        /*
         * 创建一个用于绘制树的面板并将树传入,使用绝对对齐方式
         */
//        TreePanel panel2 = new TreePanel(TreePanel.CHILD_ALIGN_ABSOLUTE);
//        panel2.setTree(n);
//        panel2.setBackground(Color.BLACK);
//        panel2.setGridColor(Color.WHITE);
//        panel2.setLinkLineColor(Color.WHITE);
//        panel2.setStringColor(Color.BLACK);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(1,1));
        contentPane.add(panel1);
//        contentPane.add(panel2);

        add(contentPane,BorderLayout.CENTER);
    }
}