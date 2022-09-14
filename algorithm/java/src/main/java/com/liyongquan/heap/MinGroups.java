package com.liyongquan.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinGroups {
    /**
     * 贪心+堆
     *
     * @param intervals
     * @return
     */
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (pq.size() > 0 && pq.peek() < start) {
                pq.poll();
            }
            pq.add(end);
        }
        return pq.size();
    }
}
