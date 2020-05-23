package cn.jlu.edu.ccst.View.Windows;

import cn.jlu.edu.ccst.Parsing.Model.AnalyseResult;
import cn.jlu.edu.ccst.Parsing.Model.Node;
import cn.jlu.edu.ccst.View.Component.TreePanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class ProgramTree extends JDialog {
    private HashMap<Integer, Integer> layerHashMap;

    public ProgramTree(AnalyseResult result) {
        this.setTitle("语法树");
        this.setModal(true);

        Node root = result.getTree().getRoot();
        layerHashMap = result.getTree().getChildNumOfLayer();

        this.setLayout(new BorderLayout());
        this.setBounds(0, 0, 900, 800);

        TreePanel panel = new TreePanel(TreePanel.CHILD_ALIGN_RELATIVE);
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

        setBounds(100, 100, 1200, 600);
        this.setVisible(true);
        //this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);



    }

}