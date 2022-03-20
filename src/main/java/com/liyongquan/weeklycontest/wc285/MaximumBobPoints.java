package com.liyongquan.weeklycontest.wc285;

public class MaximumBobPoints {
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int k = 12;
        int n = numArrows;
        int[][] dp = new int[n + 1][k];
        //选和不选
        int[][] path = new int[n + 1][k];
        //初始化
        for (int i = 0; i < k; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < k; j++) {
                //不争
                dp[i][j] = dp[i][j - 1];
                if (i > aliceArrows[j]) {
                    //要赢
                    int choose = dp[i - aliceArrows[j] - 1][j - 1] + j;
                    if (choose > dp[i][j]) {
                        path[i][j] = 1;
                        dp[i][j] = choose;
                    }
                }
            }
        }
        //构成结果
        int[] res = new int[k];
        int arrow = n;
        for (int i = k - 1; i > 0; i--) {
            if (path[arrow][i] == 1) {
                res[i] = aliceArrows[i] + 1;
                arrow -= res[i];
            }
        }
        res[0] = arrow;
        return res;
    }
}
