package com.liyongquan.weeklycontest.wc304;

public class LongestCycle {
    public int longestCycle(int[] edges) {
        int len = edges.length;
        boolean[] visit = new boolean[len];
        int res = -1;
        for (int i = 0; i < len; i++) {
            int from = i;
            if (visit[from]) {
                continue;
            }
            int to = edges[i];
            boolean[] v2 = new boolean[len];
            while (from >= 0 && !visit[from] && !v2[from]) {
                visit[from] = true;
                v2[from] = true;
                from = edges[from];
            }
            //有环，算一遍
            if (from >= 0 && v2[from]) {
                int cnt = 1;
                int tmp = from;
                while (edges[tmp] != from) {
                    cnt++;
                    tmp = edges[tmp];
                }
                res = Math.max(res, cnt);
            }
        }
        return res;
    }
}
