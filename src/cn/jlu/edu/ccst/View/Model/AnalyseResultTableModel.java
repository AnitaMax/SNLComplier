package cn.jlu.edu.ccst.View.Model;

import cn.jlu.edu.ccst.Parsing.Model.AnalyseResult;

import javax.swing.table.AbstractTableModel;

public class AnalyseResultTableModel extends AbstractTableModel {
    final static public int ColumnCount=4;//log 3项+序号
    final static public int NumOfIndex=0;//序号所在的位置
    final static public int NumOfStack=1;//堆栈所在的位置
    final static public int NumOfInput=2;//输入所在的位置
    final static public int NumOfAction=3;//动作所在的位置

    AnalyseResult analyseResult;
    String[] columnNames = {"序号", "分析栈", "输入Token", "动作"};
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    public AnalyseResultTableModel(AnalyseResult analyseResult) {
        this.analyseResult = analyseResult;
    }

    @Override
    public int getRowCount() {
        return analyseResult.getLogs().size();
    }

    @Override
    public int getColumnCount() {
        return ColumnCount;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var log=analyseResult.getLogs();
        if (rowIndex<log.size()){
            switch (columnIndex){
                case NumOfIndex:
                    return rowIndex;
                case NumOfStack:
                    return log.get(rowIndex).getStack();
                case NumOfInput:
                    return log.get(rowIndex).getInput();
                case NumOfAction:
                    return log.get(rowIndex).getAction();

            }
        }
        return null;
    }
}
