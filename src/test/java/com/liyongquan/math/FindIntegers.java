package com.liyongquan.math;

//600. 不含连续1的非负整数
//给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。
//
//示例 1:
//
//输入: 5
//输出: 5
//解释:
//下面是带有相应二进制表示的非负整数<= 5：
//0 : 0
//1 : 1
//2 : 10
//3 : 11
//4 : 100
//5 : 101
//其中，只有整数3违反规则（有两个连续的1），其他5个满足规则。
//说明: 1 <= n <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/non-negative-integers-without-consecutive-ones
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyongquan
 * @date 2021/9/11
 */
public class FindIntegers {
    /**
     * 首先暴力肯定是不可行的。假设数字的长度是n，
     * 那么以0,1结尾符合要求的数字数量分别为f0(n),f1(n)
     * <p>
     * f0(n)=f0(n-1)+f1(n-1)
     * f1(n)=f0(n-1)
     *
     * @param n
     * @return
     */
    public int findIntegers(int n) {
        if (n <= 1) {
            return n + 1;
        }
        List<Integer> list = convert(n);
        int len = list.size();
        //dp方程
        int[][] dp = new int[2][len];
        dp[0][0] = 1;
        dp[1][0] = 1;
        for (int i = 1; i < len; i++) {
            dp[0][i] = dp[0][i - 1] + dp[1][i - 1];
            dp[1][i] = dp[0][i - 1];
        }
        //先统计前n-1位的数量
        int res = dp[0][len - 2] + dp[1][len - 2];
        //从高位向低位遍历
        for (int i = len - 2; i >= 0; i--) {
            //前一位是0
            if (list.get(i + 1) == 0) {
                if (i == 0) {
                    res += list.get(i) + 1;
                } else {
                    if (list.get(i) == 1) {
                        res += dp[0][i - 1] + dp[1][i - 1];
                    }
                }
            } else {
                //前一位是1
                if (i == 0) {
                    res += 1;
                } else {
                    if (list.get(i) == 1) {
                        res += dp[0][i - 1] + dp[1][i - 1];
                    }
                }
            }
        }
        return res;
    }

    /**
     * 转化成二进制，低位在前面
     *
     * @param num
     * @return
     */
    private List<Integer> convert(int num) {
        List<Integer> res = new ArrayList<>();
        while (num > 0) {
            res.add(num & 0b1);
            num >>= 1;
        }
        if (res.size() == 0) {
            res.add(0);
        }
        return res;
    }

    /**
     * 回溯解法
     *
     * 超时
     *
     * @param n
     * @return
     */
    public int findIntegers2(int n) {
        if (n <= 1) {
            return n + 1;
        }
        List<Integer> list = convert(n);
        int len = list.size();
        //翻转数组
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = list.get(len - i - 1);
        }
        return backtrace(new int[len], len, 0, arr, true);
    }

    /**
     * @param path
     * @param len
     * @param idx
     * @param arr
     * @param equal 表示前面的[0,idx-1]是否相等
     * @return
     */
    private int backtrace(int[] path, int len, int idx, int[] arr, boolean equal) {
        if (idx == len) {
            return 1;
        }
        int res = 0;
        path[idx] = 0;
        res += backtrace(path, len, idx + 1, arr, equal && arr[idx] == 0);
        if ((idx == 0 || path[idx - 1] == 0) && (!equal || arr[idx] == 1)) {
            path[idx] = 1;
            res += backtrace(path, len, idx + 1, arr, equal && arr[idx] == 1);
        }
        return res;
    }
}
