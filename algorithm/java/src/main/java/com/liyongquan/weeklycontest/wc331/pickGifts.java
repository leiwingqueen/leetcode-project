package com.liyongquan.weeklycontest.wc331;

import java.util.Comparator;
import java.util.PriorityQueue;

public class pickGifts {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        long sum = 0;
        for (int gift : gifts) {
            queue.add(gift);
            sum += gift;
        }
        for (int i = 0; i < k; i++) {
            Integer poll = queue.poll();
            int sqrt = (int) Math.sqrt(poll);
            queue.add(sqrt);
            sum -= poll - sqrt;
        }
        return sum;
    }
}
