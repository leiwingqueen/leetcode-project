package com.liyongquan.weeklycontest.wc309;

public class NumberOfWays {
    public int numberOfWays(int startPos, int endPos, int k) {
        int mod = 1_000_000_007;
        int d = Math.abs(endPos - startPos);
        if (d > k) {
            return 0;
        }
        if (d == k) {
            return 1;
        }
        int[][] dp = new int[k + 1][k + 2];
        dp[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    dp[i][j] = (dp[i][j] + 2 * dp[i - 1][j + 1]) % mod;
                } else {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
                }
            }
        }
        return dp[k][d];
    }
}
