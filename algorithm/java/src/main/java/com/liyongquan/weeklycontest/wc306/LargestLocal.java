package com.liyongquan.weeklycontest.wc306;

public class LargestLocal {
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] res = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                //[i,j],[i+2,j+2]
                int mx = 0;
                for (int k = i; k <= i + 2; k++) {
                    for (int l = j; l <= j + 2; l++) {
                        mx = Math.max(mx, grid[k][l]);
                    }
                }
                res[i][j] = mx;
            }
        }
        return res;
    }
}
