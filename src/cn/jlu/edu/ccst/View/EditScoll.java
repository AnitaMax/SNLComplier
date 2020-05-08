package cn.jlu.edu.ccst.View;

import javax.swing.*;

public class EditScoll extends JScrollPane{
    public EditPanel getEditPanel() {
        return editPanel;
    }

    EditPanel editPanel;
    public EditScoll() {
        editPanel =new EditPanel();
        this.setViewportView(editPanel);
        editPanel.setShowLineNumber(true);
    }
}
