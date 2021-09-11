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
            return n;
        }
        List<Integer> list = convert(n);
        int len = list.size();
        //dp方程
        int[][] dp = new int[2][len];
        dp[0][0] = 1;
        dp[0][1] = 1;
        for (int i = 1; i < len; i++) {
            dp[0][i] = dp[0][i - 1] + dp[1][i - 1];
            dp[1][i] = dp[0][i - 1];
        }
        //计算前缀和，方便统计前N位数字的合法数字总数[0,2^n-1]
        int[] prefix = new int[len + 1];
        prefix[0] = 0;
        for (int i = 1; i <= len; i++) {
            prefix[i] = prefix[i - 1] + dp[0][i - 1] + dp[1][i - 1];
        }
        //先统计前n-1位的数量
        int res = prefix[len - 1];
        //从高位向低位遍历
        for (int i = len - 2; i >= 0; i--) {
            //前一位是0
            if (list.get(i + 1) == 0) {
                if (i == 0) {
                    res += list.get(i) + 1;
                } else {
                    if (list.get(i) == 1) {
                        res += prefix[i];
                    }
                }
            } else {
                //前一位是1
                if (list.get(i) == 1) {
                    res += prefix[i];
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
}
