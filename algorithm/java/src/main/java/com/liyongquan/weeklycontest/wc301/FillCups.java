package com.liyongquan.weeklycontest.wc301;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FillCups {
    /**
     * 很挫的解法
     *
     * @param amount
     * @return
     */
    public int fillCups(int[] amount) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i : amount) {
            if (i > 0) {
                pq.add(i);
            }
        }
        int res = 0;
        while (pq.size() > 0) {
            Integer p1 = pq.poll();
            if (pq.size() == 0) {
                res += p1;
                return res;
            }
            Integer p2 = pq.poll();
            res++;
            if (p1 - 1 > 0) {
                pq.add(p1 - 1);
            }
            if (p2 - 1 > 0) {
                pq.add(p2 - 1);
            }
        }
        return res;
    }

    public int fillCups2(int[] amount) {
        Arrays.sort(amount);
        int res = 0;
        res += amount[0];
        amount[2] -= amount[0];
        int min = Math.min(amount[2], amount[1]);
        res += min;
        amount[2] -= min;
        amount[1] -= min;
        res += amount[1] + amount[2];
        return res;
    }

    public int fillCups3(int[] amount) {
        Arrays.sort(amount);
        int res = 0;
        res += amount[0];
        amount[2] -= amount[0];
        int min = Math.min(amount[2], amount[1]);
        res += min;
        amount[2] -= min;
        amount[1] -= min;
        res += amount[1] + amount[2];
        return res;
    }
}
