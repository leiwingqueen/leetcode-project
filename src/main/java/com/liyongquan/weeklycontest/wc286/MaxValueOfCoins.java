package com.liyongquan.weeklycontest.wc286;

import java.util.ArrayList;
import java.util.List;

public class MaxValueOfCoins {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        //提前计算前缀和
        List<int[]> preSum = new ArrayList<>(n);
        for (List<Integer> pile : piles) {
            int[] sum = new int[pile.size() + 1];
            int idx = 1;
            for (Integer p : pile) {
                sum[idx] = sum[idx - 1] + p;
                idx++;
            }
            preSum.add(sum);
        }
        //dp初始化
        int[][] dp = new int[n][k + 1];
        int[] pre = preSum.get(0);
        for (int i = 1; i <= Math.min(k, pre.length - 1); i++) {
            dp[0][i] = pre[i];
        }
        //dp迭代
        for (int i = 1; i < n; i++) {
            int[] sum = preSum.get(i);
            for (int j = 1; j <= k; j++) {
                for (int l = 0; l <= Math.min(j, sum.length - 1); l++) {
                    dp[i][j] = Math.max(dp[i - 1][j - l] + sum[l], dp[i][j]);
                }
            }
        }
        return dp[n - 1][k];
    }
}
