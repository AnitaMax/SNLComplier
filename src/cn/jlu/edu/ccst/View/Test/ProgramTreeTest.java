package cn.jlu.edu.ccst.View.Test;

import cn.jlu.edu.ccst.Parsing.Controller.LL1Machine;
import cn.jlu.edu.ccst.Parsing.Model.SNLProdcutionElement;
import cn.jlu.edu.ccst.View.Util.CodeUtil;
import cn.jlu.edu.ccst.View.Windows.ProgramTree;
import cn.jlu.edu.ccst.WordsAnalyse.util.InfoUtil;
import cn.jlu.edu.ccst.WordsAnalyse.util.TokenUtil;

class ProgramTreeTest {

    public static void main(String[] args){
        var code= CodeUtil.DefaultCode+"kkkkk";
        var lL1Machine=new LL1Machine();
        InfoUtil.initialize();
        TokenUtil.doToken(code);
        var tokens=InfoUtil.tokenList;
        var result=lL1Machine.parsing(tokens, SNLProdcutionElement.getStartElement());

        new ProgramTree(result);
    }

}