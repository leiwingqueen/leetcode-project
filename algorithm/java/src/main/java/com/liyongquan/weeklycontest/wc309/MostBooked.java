package com.liyongquan.weeklycontest.wc309;

import com.liyongquan.design.ParkingSystem;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MostBooked {
    public int mostBooked(int n, int[][] meetings) {
        PriorityQueue<Pair<Long, Integer>> pq = new PriorityQueue<Pair<Long, Integer>>((o1, o2) -> {
            // 结束时间，房间ID
            if (o1.getKey().longValue() != o2.getKey().longValue()) {
                return o1.getKey().longValue() > o2.getKey().longValue() ? 1 : -1;
            }
            return o1.getValue() - o2.getValue();
        });
        PriorityQueue<Integer> freelist = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            freelist.add(i);
        }
        int[] cnt = new int[n];
        long time = 0;
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[0]));
        int p = 0;
        while (p < meetings.length) {
            int[] meeting = meetings[p];
            // 查看有没已经结束的会议
            while (pq.size() > 0 && pq.peek().getKey() <= time) {
                freelist.add(pq.poll().getValue());
            }
            if (time >= meeting[0]) {
                //会议要开始
                if (freelist.size() > 0) {
                    Integer room = freelist.poll();
                    pq.offer(new Pair<>(time + meeting[1] - meeting[0], room));
                    cnt[room]++;
                    p++;
                } else {
                    // 时间顺延
                    time = pq.peek().getKey();
                }
            } else {
                time = meeting[0];
            }
        }
        int idx = -1;
        int mx = 0;
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] > mx) {
                idx = i;
                mx = cnt[i];
            }
        }
        return idx;
    }
}
