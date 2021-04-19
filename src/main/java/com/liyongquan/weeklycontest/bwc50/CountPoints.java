package com.liyongquan.weeklycontest.bwc50;

public class CountPoints {
    public int[] countPoints(int[][] points, int[][] queries) {
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int cnt = 0;
            for (int[] point : points) {
                int x = queries[i][0] - point[0];
                int y = queries[i][1] - point[1];
                if (x * x + y * y <= queries[i][2] * queries[i][2]) {
                    cnt++;
                }
            }
            res[i] = cnt;
        }
        return res;
    }
}
