package com.liyongquan.weeklycontest.wc413;

import java.util.PriorityQueue;

public class ResultsArray {
    public int[] resultsArray(int[][] queries, int k) {
        int n = queries.length;
        // 大顶堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            int dis = Math.abs(x) + Math.abs(y);
            pq.offer(dis);
            if (pq.size() > k) {
                pq.poll();
            }
            if (pq.size() < k) {
                res[i] = -1;
            } else {
                res[i] = pq.peek();
            }
        }
        return res;
    }
}
