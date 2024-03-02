package com.liyongquan.weeklycontest.bwc125;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinOperations {
    public int minOperations(int[] nums, int k) {
        Arrays.sort(nums);
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
        for (int num : nums) {
            pq.add(num);
        }
        int res = 0;
        while (pq.size() > 1 && pq.peek() < k) {
            Integer num1 = pq.poll();
            Integer num2 = pq.poll();
            pq.offer(num1 * 2 + num2);
            res++;
        }
        return res;
    }
}
