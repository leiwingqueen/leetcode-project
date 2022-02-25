package com.liyongquan.math;

//537. 复数乘法
//复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
//
//实部 是一个整数，取值范围是 [-100, 100]
//虚部 也是一个整数，取值范围是 [-100, 100]
//i2 == -1
//给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
//
// 
//
//示例 1：
//
//输入：num1 = "1+1i", num2 = "1+1i"
//输出："0+2i"
//解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
//示例 2：
//
//输入：num1 = "1+-1i", num2 = "1+-1i"
//输出："0+-2i"
//解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
// 
//
//提示：
//
//num1 和 num2 都是有效的复数表示。
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/complex-number-multiplication
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class ComplexNumberMultiply {
    public String complexNumberMultiply(String num1, String num2) {
        int[] n1 = convert(num1);
        int[] n2 = convert(num2);
        int p1 = n1[0] * n2[0] - n1[1] * n2[1];
        int p2 = n1[1] * n2[0] + n1[0] * n2[1];
        return p1 + "+" + p2 + "i";
    }

    private int[] convert(String s) {
        String[] split = s.split("\\+");
        int n1 = Integer.parseInt(split[0]);
        int n2 = Integer.parseInt(split[1].substring(0, split[1].length() - 1));
        return new int[]{n1, n2};
    }
}
