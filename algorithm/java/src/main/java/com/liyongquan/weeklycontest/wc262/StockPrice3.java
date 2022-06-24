package com.liyongquan.weeklycontest.wc262;

import java.util.*;

/**
 * 还有一种延迟删的解法
 */
public class StockPrice3 {
    private int lastTime;
    //这里改成用hashmap效率会有提升,O(logn)->O(1)
    private Map<Integer, Integer> m1;
    private PriorityQueue<int[]> min;
    private PriorityQueue<int[]> max;

    public StockPrice3() {
        m1 = new HashMap<>();
        min = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        max = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        lastTime = 0;
    }

    public void update(int timestamp, int price) {
        m1.put(timestamp, price);
        min.offer(new int[]{price, timestamp});
        max.offer(new int[]{price, timestamp});
        lastTime = Math.max(lastTime, timestamp);
    }

    public int current() {
        return m1.get(lastTime);
    }

    public int maximum() {
        while (true) {
            int[] peek = max.peek();
            if (m1.get(peek[1]) == peek[0]) {
                return peek[0];
            }
            max.poll();
        }
    }

    public int minimum() {
        while (true) {
            int[] peek = min.peek();
            if (m1.get(peek[1]) == peek[0]) {
                return peek[0];
            }
            min.poll();
        }
    }
}
