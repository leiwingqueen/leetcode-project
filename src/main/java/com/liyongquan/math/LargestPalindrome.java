package com.liyongquan.math;

//给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。
//
// 
//
//示例 1:
//
//输入：n = 2
//输出：987
//解释：99 x 91 = 9009, 9009 % 1337 = 987
//示例 2:
//
//输入： n = 1
//输出： 9
// 
//
//提示:
//
//1 <= n <= 8
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/largest-palindrome-product
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class LargestPalindrome {
    public int largestPalindrome(int n) {
        int mod = 1337;
        //n位数的范围
        int pMax = (int) Math.pow(10, n) - 1;
        int pMin = (int) Math.pow(10, n - 1);
        //遍历左半部分
        for (int i = pMax; i >= pMin; i--) {
            for (int j = pMax; j >= pMin; j--) {
                //还原回文数
                int k = i;
                int p = i;
                while (k > 0) {
                    p = p * 10 + k % 10;
                    k /= 10;
                }
                if (p % j == 0 && p / j >= pMin && p / j <= pMax) {
                    return p % mod;
                }
                k = i / 10;
                p = i;
                while (k > 0) {
                    p = p * 10 + k % 10;
                    k /= 10;
                }
                if (p % j == 0 && p / j >= pMin && p / j <= pMax) {
                    return p % mod;
                }
            }
        }
        return -1;
    }
}
