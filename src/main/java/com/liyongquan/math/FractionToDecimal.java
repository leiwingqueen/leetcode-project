package com.liyongquan.math;

//给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以 字符串形式返回小数 。
//
//如果小数部分为循环小数，则将循环的部分括在括号内。
//
//如果存在多个答案，只需返回 任意一个 。
//
//对于所有给定的输入，保证 答案字符串的长度小于 104 。
//
// 
//
//示例 1：
//
//输入：numerator = 1, denominator = 2
//输出："0.5"
//示例 2：
//
//输入：numerator = 2, denominator = 1
//输出："2"
//示例 3：
//
//输入：numerator = 2, denominator = 3
//输出："0.(6)"
//示例 4：
//
//输入：numerator = 4, denominator = 333
//输出："0.(012)"
//示例 5：
//
//输入：numerator = 1, denominator = 5
//输出："0.2"
// 
//
//提示：
//
//-231 <= numerator, denominator <= 231 - 1
//denominator != 0
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @author liyongquan
 * @date 2021/10/3
 */
public class FractionToDecimal {
    /**
     * 相当于高精度除法运算
     *
     * @param numerator
     * @param denominator
     * @return
     */
    public String fractionToDecimal(int numerator, int denominator) {
        //为了避免除法之后越界
        long num = numerator;
        long den = denominator;
        if (num % den == 0) {
            return String.valueOf(num / den);
        }
        StringBuilder sb = new StringBuilder();
        //符号位处理
        if (num > 0 && den < 0 || num < 0 && den > 0) {
            sb.append("-");
        }
        num = Math.abs(num);
        den = Math.abs(den);
        //整数部分
        long p1 = num / den;
        sb.append(p1);
        if (num % den == 0) {
            return sb.toString();
        }
        sb.append(".");
        //小数部分
        //重复小数处理
        Set<Long> set = new HashSet<>();
        Stack<Long> stack = new Stack<>();
        long p2 = num - p1 * den;
        while (p2 != 0) {
            if (!set.contains(p2)) {
                stack.add(p2);
                set.add(p2);
            } else {
                //pop出结果
                StringBuilder builder = new StringBuilder();
                while (!stack.isEmpty() && stack.pop().intValue() != p2) {
                    int last = sb.length() - 1;
                    char ch = sb.charAt(last);
                    sb.deleteCharAt(last);
                    builder.append(ch);
                }
                builder.append(sb.charAt(sb.length() - 1));
                sb.deleteCharAt(sb.length() - 1);
                sb.append("(" + builder.reverse().toString() + ")");
                return sb.toString();
            }
            p2 *= 10;
            long div = p2 / den;
            p2 -= div * den;
            sb.append(div);
        }
        return sb.toString();
    }
}
