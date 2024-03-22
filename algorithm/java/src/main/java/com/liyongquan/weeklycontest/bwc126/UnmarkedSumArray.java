package com.liyongquan.weeklycontest.bwc126;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UnmarkedSumArray {
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((o1, o2) -> {
            if (nums[o1] != nums[o2]) {
                return nums[o1] - nums[o2];
            } else {
                return o1 - o2;
            }
        });
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            pq.offer(i);
            sum += nums[i];
        }
        boolean[] marked = new boolean[nums.length];
        long[] res = new long[queries.length];
        int i = 0;
        for (int[] query : queries) {
            int idx = query[0];
            if (!marked[idx]) {
                sum -= nums[idx];
            }
            marked[idx] = true;
            int k = query[1];
            int cnt = 0;
            while (cnt < k && pq.size() > 0) {
                int poll = pq.poll();
                if (!marked[poll]) {
                    sum -= nums[poll];
                    marked[poll] = true;
                    cnt++;
                }
            }
            res[i] = sum;
            i++;
        }
        return res;
    }
}
