package cn.jlu.edu.ccst.View.UI;

import cn.jlu.edu.ccst.Parsing.Controller.LL1Machine;
import cn.jlu.edu.ccst.Parsing.Model.SNLProdcutionElement;
import cn.jlu.edu.ccst.View.Model.AnalyseResultTableModel;
import cn.jlu.edu.ccst.View.Util.CodeUtil;
import cn.jlu.edu.ccst.WordsAnalyse.util.InfoUtil;
import cn.jlu.edu.ccst.WordsAnalyse.util.TokenUtil;

import javax.swing.*;
import java.awt.*;

public class Table {

    public static void main(String[] args) {
        JFrame jf = new JFrame("LL1分析过程");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        var lL1Machine=new LL1Machine();
        var s= CodeUtil.DefaultCode ;
        InfoUtil.initialize();
        TokenUtil.doToken(s);
        var tokens=InfoUtil.tokenList;
        var result=lL1Machine.parsing(tokens,SNLProdcutionElement.getStartElement());


        // 创建内容面板
        JPanel panel = new JPanel(new BorderLayout());


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

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        //table.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        JScrollPane scrollPane = new JScrollPane(table);

        // 添加 滚动面板 到 内容面板
        panel.add(scrollPane,BorderLayout.CENTER);

        // 设置 内容面板 到 窗口
        jf.setContentPane(panel);

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

}