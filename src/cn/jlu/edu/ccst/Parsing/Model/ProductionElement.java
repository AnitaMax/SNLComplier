package cn.jlu.edu.ccst.Parsing.Model;


import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;

import java.util.ArrayList;

abstract public class ProductionElement {
    boolean isEnd; //是否是非终极符
    boolean isFixed; //如果是终极符此项有效。此终极符是数字这类可变的终极符，还是保留字或者分隔符这类固定的
    String Content; //存名字 如Product 或者 内容 如:=或PROGRAM或INT或EPSILON
    ArrayList<Production> productionsStartedWiththis=new ArrayList<>();//非终极符有效。以此非终极符开始的产生式。

    ArrayList<ProductionElement> firstSet=new ArrayList<>();
    ArrayList<ProductionElement> followSet=new ArrayList<>();

    public ProductionElement(boolean isEnd, boolean isFixed, String content) {
        this.isEnd = isEnd;
        this.isFixed = isFixed;
        Content = content;
    }


    abstract public boolean accept(Token token);
    public ProductionElement() {
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public ArrayList<Production> getProductionsStartedWiththis() {
        return productionsStartedWiththis;
    }

    public void setProductionsStartedWiththis(ArrayList<Production> productionsStartedWiththis) {
        this.productionsStartedWiththis = productionsStartedWiththis;
    }

    public ArrayList<ProductionElement> getFirstSet() {
        return firstSet;
    }

    public void setFirstSet(ArrayList<ProductionElement> firstSet) {
        this.firstSet = firstSet;
    }

    public ArrayList<ProductionElement> getFollowSet() {
        if(followSet==null){
            followSet=new ArrayList<ProductionElement>();
        }
        return followSet;
    }

    public void setFollowSet(ArrayList<ProductionElement> followSet) {
        this.followSet = followSet;
    }

    @Override
    public String toString() {
        String firstSetStr=" ";
        for(int i=0;i<firstSet.size();i++){
            firstSetStr=firstSet.get(i).getContent()+firstSetStr;
        }
        String followSetStr=" ";
        if(!isEnd){
            for(int i=0;i<followSet.size();i++){
                followSetStr=followSet.get(i).getContent()+followSetStr;
            }
        }
        return "ProductionElement{" +
                " Content='" + Content + '\'' +
                ", firstSet=" + firstSetStr +
                ", followSet=" + followSetStr +
                '}';
    }
}
