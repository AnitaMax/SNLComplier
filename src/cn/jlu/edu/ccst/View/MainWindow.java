package cn.jlu.edu.ccst.View;

import cn.jlu.edu.ccst.WordsAnalyse.util.TokenUtil;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainWindow extends JFrame {
    JPanel buttonPanel;//存放按钮
    JButton wordAnalyseButton;//词法分析按钮
    JButton parsingButton; //语法分析按钮
    EditScoll codeTextPanel;
    EditScoll tokensTextPanel;
    public static void main(String[] args) {
        new MainWindow();
    }

    void setFrame(){
        this.setTitle("SNL编译器");
        this.setSize(1000,600);
        this.setLocation(200,100);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MainWindow() throws HeadlessException {
        wordAnalyseButton = new JButton("词法分析");
        wordAnalyseButton.addActionListener(e -> {
            String code=codeTextPanel.getEditPanel().getText();
            var tokens= TokenUtil.getToken(code);
            System.out.println(tokens);
            tokensTextPanel.getEditPanel().setText(tokens);
        });
        parsingButton = new JButton("语法分析");

        buttonPanel=new JPanel(new GridLayout(1,2));
        buttonPanel.add(wordAnalyseButton);
        buttonPanel.add(parsingButton);


        codeTextPanel=new EditScoll();
        tokensTextPanel = new EditScoll();


        var textPanel=new JPanel(new GridLayout(1,2));
        textPanel.add(codeTextPanel);
        textPanel.add(tokensTextPanel);

        this.add(buttonPanel,BorderLayout.NORTH);
        this.add(textPanel,BorderLayout.CENTER);


        setFrame();

    }
}
