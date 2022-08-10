package com.liyongquan.weeklycontest.wc301;

import java.util.Arrays;

public class IdealArrays {
    /**
     * 淳朴的DP解法
     *
     * 还没通过
     *
     * @param n
     * @param maxValue
     * @return
     */
    public int idealArrays(int n, int maxValue) {
        int[] dp = new int[maxValue];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            int[] tmp = new int[maxValue];
            tmp[0] = 1;
            for (int j = 1; j < maxValue; j++) {
                tmp[j] += dp[0];
                //求j的所有的因子
                for (int k = 1; k <= Math.sqrt(maxValue); k++) {
                    while (maxValue % k == 0) {
                        maxValue /= k;
                        tmp[j] += dp[maxValue];
                    }
                }
            }
            dp = tmp;
        }
        int res = 0;
        for (int i = 0; i < maxValue; i++) {
            res += maxValue;
        }
        return res;
    }
}
