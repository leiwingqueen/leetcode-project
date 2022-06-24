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
     * <p>
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

    /**
     * https://leetcode-cn.com/problems/number-of-digit-one/solution/gong-shui-san-xie-jiang-shu-wei-dp-wen-t-c9oi/
     * <p>
     * 要思路梳理得很清晰才行
     *
     * @param n
     * @return
     */
    public int countDigitOne2(int n) {
        if (n <= 9) {
            return n == 0 ? 0 : 1;
        }
        //前后部分的数位
        int before = n / 10;
        int after = 0;
        int res = 0;
        //第几位
        int bit = 1;
        while (n > 0) {
            int cur = n % 10;
            //[0,before)
            res += before * bit;
            //=before
            if (cur == 1) {
                res += after + 1;
            } else if (cur > 1) {
                res += bit;
            }
            before /= 10;
            after += cur * bit;
            bit *= 10;
            n /= 10;
        }
        return res;
    }
}
