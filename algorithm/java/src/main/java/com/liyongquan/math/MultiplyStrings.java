package com.liyongquan.math;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MultiplyStrings {
    /**
     * 有点脱裤子放屁的味道
     * <p>
     * 是否就类似于BigDecimal的实现？
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.length() == 1 && num2.length() == 1) {
            int n1 = num1.charAt(0) - '0';
            int n2 = num2.charAt(0) - '0';
            //相当于直接99乘法表
            return String.valueOf(n1 * n2);
        }
        String sum = "0";
        int shift = 0;
        if (num1.length() > 1) {
            //拆分
            for (int i = num1.length() - 1; i >= 0; i--) {
                //相乘后左移N位相加
                String multiply = multiply(String.valueOf(num1.charAt(i)), num2);
                sum = add(sum, leftShift(multiply, shift));
                shift++;
            }
        } else {
            for (int i = num2.length() - 1; i >= 0; i--) {
                String multiply = multiply(num1, String.valueOf(num2.charAt(i)));
                sum = add(sum, leftShift(multiply, shift));
                shift++;
            }
        }
        return sum;
    }

    /**
     * 使用左移代表*10
     *
     * @param num
     * @param shift
     * @return
     */
    private String leftShift(String num, int shift) {
        if (num.equals("0") || shift == 0) {
            return num;
        }
        StringBuilder sb = new StringBuilder(num.length() + shift);
        for (int i = 0; i < num.length(); i++) {
            sb.append(num.charAt(i));
        }
        for (int i = 0; i < shift; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /**
     * 高精度加法
     *
     * @param num1
     * @param num2
     * @return
     */
    private String add(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int plus = 0;
        int p1 = num1.length() - 1, p2 = num2.length() - 1;
        while (p1 >= 0 || p2 >= 0 || plus > 0) {
            int n1 = 0, n2 = 0;
            if (p1 >= 0) {
                n1 = num1.charAt(p1--) - '0';
            }
            if (p2 >= 0) {
                n2 = num2.charAt(p2--) - '0';
            }
            int sum = n1 + n2 + plus;
            if (sum > 9) {
                sb.append(sum - 10);
                plus = 1;
            } else {
                sb.append(sum);
                plus = 0;
            }
        }
        return sb.reverse().toString();
    }

}
