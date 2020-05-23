package cn.jlu.edu.ccst.View.Component;

import javax.swing.*;

public class EditScoll extends JScrollPane{
    public JTextPane getEditPanel() {
        return editPanel;

    }

    EditPanel editPanel;

    public EditScoll() {
        editPanel =new EditPanel();
        this.setViewportView(editPanel);
        editPanel.setShowLineNumber(true);

    }
}

//public class EditScoll extends JScrollPane{
//    public JTextArea getEditPanel() {
//        return jTextArea;
//    }
//
//    JTextArea jTextArea;
//    public EditScoll() {
//
//        jTextArea=new JTextArea();
//        this.setViewportView(jTextArea);
//        this.setRowHeaderView(new LineNumberHeaderView());
//    }
//}
