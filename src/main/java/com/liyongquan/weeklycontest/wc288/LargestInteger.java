package com.liyongquan.weeklycontest.wc288;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LargestInteger {
    public int largestInteger(int num) {
        PriorityQueue<Integer> pq1 = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> pq2 = new PriorityQueue<>((o1, o2) -> o2 - o1);
        List<Integer> arr = new ArrayList<>();
        while (num > 0) {
            int mod = num % 10;
            if (mod % 2 == 0) {
                pq1.add(mod);
            } else {
                pq2.add(mod);
            }
            num /= 10;
            arr.add(mod % 2);
        }
        int res = 0;
        for (int i = arr.size() - 1; i >= 0; i--) {
            if (arr.get(i) == 0) {
                res = res * 10 + pq1.poll();
            } else {
                res = res * 10 + pq2.poll();
            }
        }
        return res;
    }
}
