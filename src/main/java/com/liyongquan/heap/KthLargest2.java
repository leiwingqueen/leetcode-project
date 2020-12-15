package com.liyongquan.heap;

import java.util.PriorityQueue;

/**
 * 使用优先级队列
 */
public class KthLargest2 {
    private PriorityQueue<Integer> pq;

    private int cap;

    public KthLargest2(int k, int[] nums) {
        cap = k;
        pq = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (pq.size() < cap) {
            pq.add(val);
        } else if (pq.peek() < val) {
            pq.poll();
            pq.add(val);
        }
        return pq.peek();
    }
}
