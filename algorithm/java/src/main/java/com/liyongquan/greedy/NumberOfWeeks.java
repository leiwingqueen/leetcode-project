package com.liyongquan.greedy;

import java.util.PriorityQueue;

public class NumberOfWeeks {
    public long numberOfWeeks(int[] milestones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int milestone : milestones) {
            pq.add(milestone);
        }
        long res = 0;
        while (pq.size() >= 2) {
            Integer p1 = pq.poll();
            Integer p2 = pq.poll();
            int d = Math.min(p1, p2);
            p1 -= d;
            p2 -= d;
            res += 2 * (long) d;
            if (p1 > 0) {
                pq.add(p1);
            }
            if (p2 > 0) {
                pq.add(p2);
            }
        }
        if (pq.size() > 0) {
            res++;
        }
        return res;
    }

    // 超时
    public long numberOfWeeks2(int[] milestones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int milestone : milestones) {
            pq.add(milestone);
        }
        long res = 0;
        while (pq.size() > 2) {
            Integer p1 = pq.poll();
            Integer p2 = pq.poll();
            Integer p3 = pq.peek();
            int d = p2 - p3 + 1;
            p1 -= d;
            p2 -= d;
            res += 2 * (long) d;
            if (p1 > 0) {
                pq.add(p1);
            }
            if (p2 > 0) {
                pq.add(p2);
            }
        }
        if (pq.size() == 2) {
            Integer p1 = pq.poll();
            Integer p2 = pq.poll();
            res += 2L * p2;
            p1 -= p2;
            if (p1 > 0) {
                pq.add(p1);
            }
        }
        if (!pq.isEmpty()) {
            res++;
        }
        return res;
    }
}
