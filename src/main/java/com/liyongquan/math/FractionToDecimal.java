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

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/10/3
 */
public class FractionToDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        //整数部分
        int p1 = numerator / denominator;
        sb.append(p1);
        if (numerator % denominator == 0) {
            return sb.toString();
        }
        sb.append(".");
        //小数部分
        //重复小数处理
        Set<Integer> set = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        int p2 = numerator - p1 * denominator;
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
            int div = p2 / denominator;
            p2 -= div * denominator;
            sb.append(div);
        }
        return sb.toString();
    }
}
