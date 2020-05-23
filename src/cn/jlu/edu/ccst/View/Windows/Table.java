package cn.jlu.edu.ccst.View.Windows;

import cn.jlu.edu.ccst.Parsing.Model.AnalyseResult;
import cn.jlu.edu.ccst.View.Model.AnalyseResultTableModel;

import javax.swing.*;
import java.awt.*;

public class Table extends JDialog{

    public Table(AnalyseResult result){

        this.setTitle("LL1分析过程");
        this.setModal(true);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // 创建内容面板
        JPanel panel = new JPanel(new BorderLayout());

        //结果Label
        var resultString="语法分析结果:";
        if (result.isSuccess()){
            resultString+="成功，无语法错误";
        }else{
            resultString+="失败，失败原因:"+result.getFailResult();
        }
        var resultLabel=new JLabel(resultString,JLabel.CENTER);
        resultLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));

        panel.add(resultLabel,BorderLayout.NORTH);

        // 创建一个表格，指定 表头 和 所有行数据
        JTable table = new JTable(new AnalyseResultTableModel(result));

        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table.setRowHeight(30);

        table.getTableHeader().setReorderingAllowed(false);   //整列移动
        table.getTableHeader().setResizingAllowed(true);   //拉动表格

        // 设置列宽
        table.getColumnModel().getColumn(AnalyseResultTableModel.NumOfIndex).setMaxWidth(40);
        table.getColumnModel().getColumn(AnalyseResultTableModel.NumOfStack).setPreferredWidth(600);
        table.getColumnModel().getColumn(AnalyseResultTableModel.NumOfInput).setPreferredWidth(200);
        table.getColumnModel().getColumn(AnalyseResultTableModel.NumOfAction).setPreferredWidth(600);


        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);


        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        //table.setPreferredScrollableViewportSize(new Dimension(scrollPane.getWidth()*2, scrollPane.getHeight()));



        // 添加 滚动面板 到 内容面板
        panel.add(scrollPane,BorderLayout.CENTER);

        // 设置 内容面板 到 窗口
        setContentPane(panel);

        pack();
        setLocationRelativeTo(null);

        setBounds(100,100,1200,600);
        setVisible(true);
    }

}