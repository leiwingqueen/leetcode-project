package com.liyongquan.string;

import java.util.Deque;
import java.util.Iterator;
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
     * 栈中只保留需要相加的数字。
     * 遇到数字入栈，如果是+-运算符不做任何处理。如果是*除则出栈并计算出结果再入栈
     * <p>
     * 最后栈中只会保留+-的运算符
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        Deque<Number> s1 = new LinkedList<>();
        Deque<Operator> s2 = new LinkedList<>();

        List<Token> tokens = lex(s);
        Iterator<Token> iterator = tokens.iterator();
        while (iterator.hasNext()) {
            Token token = iterator.next();
            if (token instanceof Number) {
                s1.offerFirst((Number) token);
            } else {
                Operator op = (Operator) token;
                if (op.op == '+' || op.op == '-') {
                    s2.offerFirst(op);
                } else {
                    Number next = (Number) iterator.next();
                    Number pre = s1.pollFirst();
                    if (op.op == '*') {
                        s1.offerFirst(new Number(next.num * pre.num));
                    } else {
                        s1.offerFirst(new Number(pre.num / next.num));
                    }
                }
            }
        }
        //最后把栈内的元素相加/相减,需要使用双端队列的特性，顺序弹出
        while (!s2.isEmpty()) {
            Number n1 = s1.pollLast();
            Number n2 = s1.pollLast();
            Operator op = s2.pollLast();
            if (op.op == '+') {
                s1.offerLast(new Number(n1.num + n2.num));
            } else {
                s1.offerLast(new Number(n1.num - n2.num));
            }
        }
        return s1.peekLast().num;
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
        if (num != null) {
            tokens.add(new Number(num));
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
