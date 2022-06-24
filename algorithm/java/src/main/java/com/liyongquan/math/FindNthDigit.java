package com.liyongquan.math;

//剑指 Offer 44. 数字序列中某一位的数字
//数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
//
//请写一个函数，求任意第n位对应的数字。
//
// 
//
//示例 1：
//
//输入：n = 3
//输出：3
//示例 2：
//
//输入：n = 11
//输出：0
// 
//
//限制：
//
//0 <= n < 2^31
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/9/5
 */
public class FindNthDigit {
    public int findNthDigit(int n) {
        int[] cnt = {0, 10, 190, 2890, 38890, 488890, 5888890, 68888890, 788888890};
        //>=n的第一个数字
        int idx = 0;
        while (idx < cnt.length && cnt[idx] < (long) n + 1) {
            idx++;
        }
        int len = idx;
        //计算对应的数字
        int num = (len == 1 ? 0 : (int) Math.pow(10, len - 1)) + (n - cnt[idx - 1]) / len;
        //计算数字的第几位
        int i = (n - cnt[idx - 1]) % len;
        int res = 0;
        for (int j = 0; j < len - i; j++) {
            res = num % 10;
            num /= 10;
        }
        return res;
    }
}
