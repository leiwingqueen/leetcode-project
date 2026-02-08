package com.liyongquan.weeklycontest.wc309;

import com.liyongquan.design.ParkingSystem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MostBooked {

    private static class Pair {
        long key;
        int value;
        Pair(long key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    public int mostBooked(int n, int[][] meetings) {
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((o1, o2) -> {
            // 结束时间，房间ID
            if (o1.key != o2.key) {
                return o1.key > o2.key ? 1 : -1;
            }
            return o1.value - o2.value;
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
            while (pq.size() > 0 && pq.peek().key <= time) {
                freelist.add(pq.poll().value);
            }
            if (time >= meeting[0]) {
                //会议要开始
                if (freelist.size() > 0) {
                    Integer room = freelist.poll();
                    pq.offer(new Pair(time + meeting[1] - meeting[0], room));
                    cnt[room]++;
                    p++;
                } else {
                    // 时间顺延
                    time = pq.peek().key;
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
