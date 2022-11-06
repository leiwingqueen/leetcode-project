package com.liyongquan.weeklycontest.wc318;

import java.util.PriorityQueue;

public class TotalCost {
    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<Integer> pq1 = new PriorityQueue<>((o1, o2) -> {
            if (costs[o1] != costs[o2]) {
                return costs[o1] - costs[o2];
            } else {
                return o1 - o2;
            }
        });
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((o1, o2) -> {
            if (costs[o1] != costs[o2]) {
                return costs[o1] - costs[o2];
            } else {
                return o1 - o2;
            }
        });
        int n = costs.length;
        boolean[] visit = new boolean[n];
        int l = 0;
        int r = n - 1;
        for (int i = 0; i < candidates; i++) {
            pq1.add(l);
            l++;
        }
        for (int i = 0; i < candidates; i++) {
            if (r < l) {
                break;
            }
            pq2.add(r);
            r--;
        }
        long res = 0;
        for (int i = 0; i < k; i++) {
            Integer p1 = pq1.peek();
            Integer p2 = pq2.peek();
            int choose = 0;
            if (p1 == null) {
                choose = p2;
            } else if (p2 == null) {
                choose = p1;
            } else {
                if (costs[p1] < costs[p2] || (costs[p1] == costs[p2] && p1 < p2)) {
                    choose = p1;
                } else {
                    choose = p2;
                }
            }
            res += costs[choose];
            visit[choose] = true;
            if (p1 != null && p1 == choose) {
                pq1.poll();
                while (l <= r && visit[l]) {
                    l++;
                }
                if (l <= r) {
                    pq1.add(l);
                    l++;
                }
            }
            if (p2 != null && p2 == choose) {
                pq2.poll();
                while (r >= l && visit[r]) {
                    r--;
                }
                if (r >= l) {
                    pq2.add(r);
                    r--;
                }
            }
        }
        return res;
    }
}
