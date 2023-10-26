package com.liyongquan.heap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MinLengthAfterRemovals {
    public int minLengthAfterRemovals(List<Integer> nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(map.size(), (o1, o2) -> o2[1] - o1[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }
        int res = nums.size();
        while (pq.size() > 1) {
            int[] p1 = pq.poll();
            int[] p2 = pq.poll();
            res -= 2;
            if (p1[1] > 1) {
                pq.add(new int[]{p1[0], p1[1] - 1});
            }
            if (p2[1] > 1) {
                pq.add(new int[]{p2[0], p2[1] - 1});
            }
        }
        return res;
    }
}
