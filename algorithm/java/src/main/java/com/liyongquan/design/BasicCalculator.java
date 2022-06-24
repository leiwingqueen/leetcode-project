package com.liyongquan.design;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 224. 基本计算器
 * <p>
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BasicCalculator {
    /**
     * 分几种场景
     * 1.普通的加减法
     * 统一转换成加法操作
     * 2.括号
     * 括号内的表达式也是一个正常的表达式，可以使用递归来解决，转化成子问题
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        List<Token> tokens = lex(s);
        return calculate(tokens, 0, tokens.size() - 1);
    }

    private int calculate(List<Token> tokens, int start, int end) {
        Deque<Integer> stack = new LinkedList<>();
        //是否正数
        boolean positive = true;
        int idx = start;
        while (idx <= end) {
            Token token = tokens.get(idx++);
            if (token instanceof Num) {
                stack.offerLast(positive ? ((Num) token).value : -((Num) token).value);
                positive = true;
            } else if (token instanceof Op) {
                Op op = (Op) token;
                if (op.op == '+') {
                    positive = true;
                } else if (op.op == '-') {
                    positive = false;
                }
            } else {
                //找到下一个括号的位置
                Deque<Character> s2 = new LinkedList<>();
                s2.offerLast('(');
                int s = idx;
                while (idx <= end && !s2.isEmpty()) {
                    Token t1 = tokens.get(idx++);
                    if (t1 instanceof Bracket) {
                        Bracket bracket = (Bracket) t1;
                        if (bracket.bracket == '(') {
                            s2.offerLast('(');
                        } else {
                            s2.pollLast();
                        }
                    }
                }
                int res = calculate(tokens, s, idx - 2);
                stack.offerLast(positive ? res : -res);
                positive = true;
            }
        }
        //计算总和
        int res = 0;
        for (Integer n : stack) {
            res += n;
        }
        return res;
    }

    /**
     * 词法分析
     *
     * @param s
     * @return
     */
    private List<Token> lex(String s) {
        int len = s.length();
        List<Token> tokens = new ArrayList<>();
        int num = 0;
        boolean numStart = false;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                numStart = true;
                num = num * 10 + (ch - '0');
            } else {
                if (numStart) {
                    tokens.add(new Num(num));
                    numStart = false;
                    num = 0;
                }
                if (ch == '(' || ch == ')') {
                    tokens.add(new Bracket(ch));
                } else if (ch == '+' || ch == '-') {
                    tokens.add(new Op(ch));
                }
                //空格直接跳过，不需要做任何事情
            }
        }
        //到达字符串尾部
        if (numStart) {
            tokens.add(new Num(num));
        }
        return tokens;
    }


    /**
     * 解法2
     * 增加一个栈来维护当前的符号位
     *
     * @param s
     * @return
     */
    public int calculate2(String s) {
        Deque<Boolean> positive = new LinkedList<>();
        positive.offerLast(true);
        int idx = 0;
        int len = s.length();
        int res = 0;
        boolean prePositive = true;
        while (idx < len) {
            char ch = s.charAt(idx);
            //数字处理
            if (ch >= '0' && ch <= '9') {
                int num = 0;
                while (idx < len && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
                    num = num * 10 + s.charAt(idx) - '0';
                    idx++;
                }
                if (positive.peekLast() && !prePositive || !positive.peekLast() && prePositive) {
                    num = -num;
                }
                res += num;
            } else {
                if (ch == '+') {
                    prePositive = true;
                } else if (ch == '-') {
                    prePositive = false;
                } else if (ch == '(') {
                    positive.offerLast(prePositive ? positive.peekLast() : !positive.peekLast());
                    prePositive = true;
                } else if (ch == ')') {
                    positive.pollLast();
                    prePositive = true;
                }
                idx++;
            }
        }
        return res;
    }
}

interface Token {
}

class Num implements Token {
    int value;

    public Num(int value) {
        this.value = value;
    }
}

class Op implements Token {
    char op;

    public Op(char op) {
        this.op = op;
    }
}

class Bracket implements Token {
    char bracket;

    public Bracket(char bracket) {
        this.bracket = bracket;
    }
}
