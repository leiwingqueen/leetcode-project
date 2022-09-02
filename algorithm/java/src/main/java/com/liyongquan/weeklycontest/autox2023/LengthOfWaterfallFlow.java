package com.liyongquan.weeklycontest.autox2023;

import java.util.PriorityQueue;

public class LengthOfWaterfallFlow {
    public int getLengthOfWaterfallFlow(int num, int[] block) {
        int[] height = new int[num];
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            if (height[o1] != height[o2]) {
                return height[o1] - height[o2];
            } else {
                return o1 - o2;
            }
        });
        for (int i = 0; i < num; i++) {
            pq.add(i);
        }
        for (int i = 0; i < block.length; i++) {
            Integer idx = pq.poll();
            height[idx] += block[i];
            pq.offer(idx);
        }
        int mx = 0;
        for (int i = 0; i < num; i++) {
            mx = Math.max(mx, height[i]);
        }
        return mx;
    }
}
