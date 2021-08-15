package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liyongquan
 * @date 2021/8/15
 */
@Slf4j
public class OutOfBoundaryPaths {
    public static final int[][] DIRS = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1},
    };

    public static final int MOD = 1000000007;

    /**
     * 指数级别复杂度
     * <p>
     * 超时，但是我们可以通过下面的逻辑改成dp表达式
     *
     * @param m
     * @param n
     * @param maxMove
     * @param startRow
     * @param startColumn
     * @return
     */
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove == 0) {
            return 0;
        }
        int res = 0;
        for (int[] dir : DIRS) {
            int x = startRow + dir[0], y = startColumn + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n) {
                res = (res + 1) % MOD;
            } else {
                res = (res + findPaths(m, n, maxMove - 1, x, y)) % MOD;
            }
        }
        return res;
    }

    /**
     * dp解法
     * <p>
     * 时间复杂度O(m*n*maxMove)
     *
     * @param m
     * @param n
     * @param maxMove
     * @param startRow
     * @param startColumn
     * @return
     */
    public int findPaths2(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] dp = new int[maxMove + 1][m][n];
        for (int k = 1; k <= maxMove; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] dir : DIRS) {
                        int x = i + dir[0], y = j + dir[1];
                        if (x < 0 || x >= m || y < 0 || y >= n) {
                            dp[k][i][j] = (dp[k][i][j] + 1) % MOD;
                        } else {
                            dp[k][i][j] = (dp[k][i][j] + dp[k - 1][x][y]) % MOD;
                        }
                    }
                }
            }
        }
        return dp[maxMove][startRow][startColumn];
    }

}
