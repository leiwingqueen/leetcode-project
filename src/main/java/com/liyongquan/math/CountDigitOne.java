package com.liyongquan.math;

//给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
//
//
//
// 示例 1：
//
//
//输入：n = 13
//输出：6
//
//
// 示例 2：
//
//
//输入：n = 0
//输出：0
//
//
//
//
// 提示：
//
//
// 0 <= n <= 2 * 109
//
// Related Topics 数学
// 👍 208 👎 0

public class CountDigitOne {
    /**
     * 暴力解法
     *
     * 超时
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int tmp = i;
            while (tmp != 0) {
                if (tmp % 10 == 1) {
                    sum++;
                }
                tmp /= 10;
            }
        }
        return sum;
    }
}
