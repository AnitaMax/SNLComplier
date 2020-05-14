package cn.jlu.edu.ccst.Parsing.Model;


import java.util.ArrayList;

public class ProductionElement {
    boolean isEnd; //是否是非终极符
    boolean isFixed; //如果是终极符此项有效。此终极符是数字这类可变的终极符，还是保留字或者分隔符这类固定的
    String Content; //存名字 如Product 或者 内容 如:=或PROGRAM或INT或EPSILON

    ArrayList<ProductionElement> firstSet;
    ArrayList<ProductionElement> endSet;
}
