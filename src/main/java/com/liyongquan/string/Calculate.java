package com.liyongquan.string;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。
 * <p>
 * 表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "3+2*2"
 * 输出: 7
 * 示例 2:
 * <p>
 * 输入: " 3/2 "
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 * <p>
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/calculator-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Calculate {
    public static final char[] OP = {'+', '-', '*', '/'};

    /**
     * 双栈解决运算符的优先级问题
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Deque<Number> num = new LinkedList<>();
        Deque<Operator> op = new LinkedList<>();
        List<Token> tokens = lex(s);
        for (Token token : tokens) {
            if (token instanceof Number) {
                num.offerFirst((Number) token);
            } else {
                op.offerFirst((Operator) token);
            }
        }
        //出栈
        while (!op.isEmpty()) {
            Operator operator = op.pollFirst();
            if (operator.op == '*' || operator.op == '/') {
                Number n1 = num.pollFirst();
                Number n2 = num.pollFirst();
                if (operator.op == '*') {
                    num.offerFirst(new Number(n1.num * n2.num));
                } else {
                    num.offerFirst(new Number(n2.num / n1.num));
                }
            } else {
                Operator preOp = op.peekFirst();
                
            }
        }
    }

    /**
     * 词法分析
     *
     * @param s
     * @return
     */
    private List<Token> lex(String s) {
        List<Token> tokens = new LinkedList<>();
        Integer num = null;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                if (num == null) {
                    num = 0;
                }
                num = num * 10 + (c - '0');
            } else {
                if (num != null) {
                    tokens.add(new Number(num));
                    num = null;
                }
                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    tokens.add(new Operator(c));
                }
            }
        }
        return tokens;
    }

    private interface Token {
    }

    private static class Number implements Token {
        int num;

        public Number(int num) {
            this.num = num;
        }
    }

    private static class Operator implements Token {
        char op;

        public Operator(char op) {
            this.op = op;
        }
    }
}
