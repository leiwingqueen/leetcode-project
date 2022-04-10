package com.liyongquan.weeklycontest.wc288;

import java.util.PriorityQueue;

public class MaximumProduct {
    public int maximumProduct(int[] nums, int k) {
        int mod = 1_000_000_007;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }
        while (k > 0) {
            Integer poll = pq.poll();
            pq.add(poll + 1);
            k--;
        }
        long res = 1;
        while (pq.size() > 0) {
            res = (res * pq.poll()) % mod;
        }
        return (int) res;
    }
}
