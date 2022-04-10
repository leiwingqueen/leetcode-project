package com.liyongquan.weeklycontest.wc288;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumBeauty {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        int mx = 0;
        long left = newFlowers;
        for (int i = flowers.length - 1; i >= 0; i--) {
            if (left <= 0) {
                break;
            }
            if (flowers[i] < target) {
                long d = Math.min(left, target - flowers[i]);
                if (flowers[i] + d >= target) {
                    mx++;
                    left -= d;
                } else {
                    break;
                }
            } else {
                mx++;
            }
        }
        if (mx == flowers.length) {
            return Math.max(full * mx, full * (mx - 1) + (target - 1) * partial);
        }
        if (left <= 0) {
            return full * mx + flowers[0] * partial;
        } else {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int flower : flowers) {
                queue.add(flower);
            }
            while (left > 0) {
                Integer poll = queue.poll();
                if (queue.size() == 0) {
                    queue.add((int) left);
                    left = 0;
                }
                Integer peek = queue.peek();
                int n = Math.min((int) left, peek - poll + 1);
                queue.add(poll + n);
                left -= n;
            }
            return full * mx + queue.peek() * partial;
        }
    }

}
