package com.liyongquan.math;

//将非负整数 num 转换为其对应的英文表示。
//
// 
//
//示例 1：
//
//输入：num = 123
//输出："One Hundred Twenty Three"
//示例 2：
//
//输入：num = 12345
//输出："Twelve Thousand Three Hundred Forty Five"
//示例 3：
//
//输入：num = 1234567
//输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
//示例 4：
//
//输入：num = 1234567891
//输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
// 
//
//提示：
//
//0 <= num <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/integer-to-english-words
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Stack;

/**
 * @author liyongquan
 * @date 2021/10/11
 */
public class NumberToWords {
    public static final String[] NUMBER = {
            "",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen",
    };

    public static final String[] TEN = {
            "",
            "TEN",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety",
    };

    public static final String[] THOUSAND = {
            "",
            " Thousand",
            " Million",
            " Billion",
    };


    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        Stack<String> stack = new Stack<>();
        //3位一组进行处理
        int idx = 0;
        while (num > 0) {
            String s = num2word(num % 1000);
            if (s.length() > 0) {
                stack.add(s + THOUSAND[idx]);
            }
            num /= 1000;
            idx++;
        }
        //输出
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * 100以内的数字转换
     *
     * @param num
     * @return
     */
    private String num2word(int num) {
        if (num == 0) {
            return "";
        }
        Stack<String> stack = new Stack<>();
        if (num % 100 < 20) {
            String s = NUMBER[num % 100];
            if (s.length() > 0) {
                stack.add(NUMBER[num % 100]);
            }
            num /= 100;
        } else {
            if (num % 10 > 0) {
                stack.add(NUMBER[num % 10]);
            }
            num /= 10;
            if (num % 10 > 0) {
                stack.add(TEN[num % 10]);
            }
            num /= 10;
        }
        if (num % 10 > 0) {
            stack.add(NUMBER[num % 10] + " Hundred");
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            String pop = stack.pop();
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(pop);
        }
        return sb.toString();
    }
}
