package cn.jlu.edu.ccst.View.Windows;

import cn.jlu.edu.ccst.Parsing.Controller.LL1Machine;
import cn.jlu.edu.ccst.Parsing.Model.SNLProdcutionElement;
import cn.jlu.edu.ccst.View.Component.EditScoll;
import cn.jlu.edu.ccst.View.Util.CodeUtil;
import cn.jlu.edu.ccst.WordsAnalyse.util.InfoUtil;
import cn.jlu.edu.ccst.WordsAnalyse.util.TokenUtil;

import javax.swing.*;
import java.awt.*;



public class MainWindow extends JFrame {
    JPanel buttonPanel;//存放按钮
    JButton wordAnalyseButton;//词法分析按钮
    JButton parsingButton; //语法分析按钮
    JButton generateTreeButton; //语法分析按钮
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
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public MainWindow() throws HeadlessException {
        wordAnalyseButton = new JButton("词法分析");
        wordAnalyseButton.addActionListener(e -> {
            String code=codeTextPanel.getEditPanel().getText();
            //System.out.println(code);
            code=code.replace("\r","");
            var tokens= TokenUtil.getToken(code);
            //System.out.println(tokens);
            tokensTextPanel.getEditPanel().setText(tokens);
        });
        parsingButton = new JButton("语法分析");
        parsingButton.addActionListener(e -> {
            String code=codeTextPanel.getEditPanel().getText();
            //System.out.println(code);
            code=code.replace("\r","");
            InfoUtil.initialize();
            TokenUtil.doToken(code);
            var tokens=InfoUtil.tokenList;
            //System.out.println(tokens);
            var lL1Machine=new LL1Machine();
            var result=lL1Machine.parsing(tokens, SNLProdcutionElement.getStartElement());
            tokensTextPanel.getEditPanel().setText(result.toString());

            new Table(result);

        });

        generateTreeButton=new JButton("生成语法树");
        generateTreeButton.addActionListener(e ->{
            String code=codeTextPanel.getEditPanel().getText();
            //System.out.println(code);
            code=code.replace("\r","");
            InfoUtil.initialize();
            TokenUtil.doToken(code);
            var tokens=InfoUtil.tokenList;
            //System.out.println(tokens);
            var lL1Machine=new LL1Machine();
            var result=lL1Machine.parsing(tokens, SNLProdcutionElement.getStartElement());
            tokensTextPanel.getEditPanel().setText(result.toString());

            if(result.isSuccess()){
                new ProgramTree(result);
            }else {
                JOptionPane.showMessageDialog(this, result.getFailResult(),
                        "编译出错", JOptionPane.ERROR_MESSAGE);
            }

        });

        buttonPanel=new JPanel(new GridLayout(1,3));
        buttonPanel.add(wordAnalyseButton);
        buttonPanel.add(parsingButton);
        buttonPanel.add(generateTreeButton);


        codeTextPanel=new EditScoll();
        tokensTextPanel = new EditScoll();


        var textPanel=new JPanel(new GridLayout(1,2));
        //var textPanel=new JPanel();
        textPanel.add(codeTextPanel);
        textPanel.add(tokensTextPanel);

        this.add(buttonPanel,BorderLayout.NORTH);
        this.add(textPanel,BorderLayout.CENTER);


        var aboutLabel=new JLabel("版权所有：郑汉亚 郑琪琛 ，盗用必究");

        this.add(aboutLabel,BorderLayout.SOUTH);
        setFrame();
        setDefaultCode();
    }

    public void setDefaultCode(){
        var code= CodeUtil.DefaultCode;
        codeTextPanel.getEditPanel().setText(code);
    }
}
