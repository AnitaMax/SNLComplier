package cn.jlu.edu.ccst.Parsing.Test.Model;

import cn.jlu.edu.ccst.Parsing.Model.SNLProdcutionElement;
import cn.jlu.edu.ccst.WordsAnalyse.Model.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductionElementTest {

    @Test
    void accept() {
        var intcEle=new SNLProdcutionElement("INTC");
        var intcToken=new Token(0,0,"INTC","11");
        var resveredEle=new SNLProdcutionElement("PROGRAM");
        var SingleSeperatorEle=new SNLProdcutionElement(";");
        var SingleSeperatorToken=new Token(0,0,"SingleSeperator",";");
        var resveredToken=new Token(0,0,"ReservedWord","PROGRAM");
        var notEndEle=new SNLProdcutionElement("Program");
        assertFalse(SingleSeperatorEle.accept(resveredToken));
        assertTrue(SingleSeperatorEle.accept(SingleSeperatorToken));
        assertTrue(intcEle.accept(intcToken));
        assertTrue(resveredEle.accept(resveredToken));
        assertFalse(intcEle.accept(resveredToken));
        assertFalse(resveredEle.accept(intcToken));
        assertFalse(notEndEle.accept(intcToken));
    }
}