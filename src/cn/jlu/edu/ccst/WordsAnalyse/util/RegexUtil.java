package cn.jlu.edu.ccst.WordsAnalyse.util;

import cn.jlu.edu.ccst.WordsAnalyse.Model.NFA;

import java.util.HashMap;
import java.util.Stack;

public class RegexUtil {
    public final static  char CONCATENATION_OPERATOR = '·';
    public final static  char UNION_OPERATOR = '|';
    public final static  char CLOSURE_OPERATOR = '*';
    public final static  char ZERO_OR_ONE_OPERATOR = '?';
    public final static  char ONE_OR_MORE_OPERATOR = '+';
    public final static  char GROUP_LEFT_OPERATOR = '(';
    public final static  char GROUP_RIGHT_OPERATOR = ')';

    static HashMap<Character, Integer> operatorPriorityMap=new HashMap<>(){{
        put(UNION_OPERATOR,1);//|
        put(CONCATENATION_OPERATOR,2);//·
        put(ZERO_OR_ONE_OPERATOR,3);//?
        put(ONE_OR_MORE_OPERATOR,3);//+
        put(CLOSURE_OPERATOR,3);//闭包 *
    }};


    /**
     * 给正则表达式增加连接符·
     * @param exp 正则表达式
     * @return 加点之后的正则表达式
     * 参考 https://segmentfault.com/a/1190000018258326
     */
    public static String insertExplicitConcatOperator(String exp){
        StringBuilder output= new StringBuilder();
        for (int i = 0; i < exp.length(); i++) {
            var token=exp.charAt(i);
            output.append(token);

            if(token==GROUP_LEFT_OPERATOR||token==GROUP_RIGHT_OPERATOR||token==UNION_OPERATOR){
                continue;
            }

            if(i<exp.length()-1){
                var lookahead=exp.charAt(i+1);

                if(lookahead==CLOSURE_OPERATOR ||
                        lookahead==ZERO_OR_ONE_OPERATOR||
                        lookahead==ONE_OR_MORE_OPERATOR||
                        lookahead==GROUP_RIGHT_OPERATOR||
                        lookahead==UNION_OPERATOR){
                    continue;
                }
                output.append(CONCATENATION_OPERATOR);
            }
        }
        return output.toString();
    }

    /**
     * 给加点后的中缀正则转化为后缀正则
     * @param exp 加点之后的正则表达式
     * @return 后缀正则
     */
    public static String infixToPostfix(String exp) {
        var output = new StringBuilder();
        var stack = new Stack<Character>();

        for (char token : exp.toCharArray()) {
            switch (token) {
                case GROUP_LEFT_OPERATOR:
                    //如果遇到左括号，将其入栈。
                    stack.push(token);
                    break;
                case GROUP_RIGHT_OPERATOR:
                    //如果遇到右括号，将栈元素弹出并输出直到遇到左括号为止。左括号只弹出不输出。
                    while (stack.size() > 0 && stack.peek() != GROUP_LEFT_OPERATOR) {
                        output.append(stack.pop());
                    }
                    stack.pop();
                    break;
                case CONCATENATION_OPERATOR :
                case UNION_OPERATOR :
                case CLOSURE_OPERATOR :
                case ZERO_OR_ONE_OPERATOR :
                case ONE_OR_MORE_OPERATOR:
                    //如果遇到限定符，依次弹出栈顶优先级大于或等于该限定符的限定符，然后将其入栈。
                    while (stack.size() > 0 &&
                            operatorPriorityMap.containsKey(stack.peek()) &&
                            operatorPriorityMap.get(stack.peek()) >= operatorPriorityMap.get(token)) {
                        output.append(stack.pop());
                    }
                    stack.push(token);
                    break;
                default:
                    //如果遇到字母，将其输出。
                    output.append(token);
            }
        }
        //如果读到了输入的末尾，则将栈中所有元素依次弹出。
        while (stack.size() != 0) {
            output.append(stack.pop());
        }
        return output.toString();
    }

    public static boolean match(String regex,String exp){
        var strWithConcat=insertExplicitConcatOperator(regex);
        var strWithPostfix=infixToPostfix(strWithConcat);
        var nfa= NFA.buildToNFA(strWithPostfix);
        return nfa.isMatch(exp);
    }
}
