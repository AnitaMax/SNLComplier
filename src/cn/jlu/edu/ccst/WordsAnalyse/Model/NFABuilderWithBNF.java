package cn.jlu.edu.ccst.WordsAnalyse.Model;

public class NFABuilderWithBNF {
    /*
    TODO:
         文法形式实现正则表达式的文法解析
     */
    /*
      正则表达式的BNF范式如下，这样我们可以采用自顶向下来分析，从最顶层的group开始向下递归
      group ::= ("(" expr ")")*
      expr ::= factor_conn ("|" factor_conn)*
      factor_conn ::= factor | factor factor*
      factor ::= (term | term ("*" | "+" | "?"))*
      term ::= char | "[" char "-" char "]" | .

      参考链接：https://juejin.im/post/5d897ee46fb9a06b317ba114
     */



}
