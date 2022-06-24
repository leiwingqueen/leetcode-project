package com.liyongquan.weeklycontest.wc297;

public class MinPathCost {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            dp[m - 1][i] = grid[m - 1][i];
        }
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    //对应的坐标
                    int v = grid[i][j];
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][k] + grid[i][j] + moveCost[v][k]);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, dp[0][i]);
        }
        return res;
    }
}
