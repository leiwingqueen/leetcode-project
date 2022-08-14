package com.liyongquan.weeklycontest.wc306;

import java.util.Arrays;

public class EdgeScore {
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[][] points = new long[n][2];
        for (int i = 0; i < n; i++) {
            int to = edges[i];
            points[to][1] += i;
            points[to][0] = to;
        }
        Arrays.sort(points, (o1, o2) -> {
            if (o2[1] != o1[1]) {
                if (o1[1] < o2[1]) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return (int) (o1[0] - o2[0]);
        });
        return (int) points[0][0];
    }
}
