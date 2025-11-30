package com.liyongquan.heap;

// 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
//
//你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。在任意一天 d 中只能参加一场会议。
//
//请你返回你可以参加的 最大 会议数目。
//
//
//
//示例 1：
//
//
//
//输入：events = [[1,2],[2,3],[3,4]]
//输出：3
//解释：你可以参加所有的三个会议。
//安排会议的一种方案如上图。
//第 1 天参加第一个会议。
//第 2 天参加第二个会议。
//第 3 天参加第三个会议。
//示例 2：
//
//输入：events= [[1,2],[2,3],[3,4],[1,2]]
//输出：4
//
//
//提示：​​​​​​
//
//1 <= events.length <= 105
//events[i].length == 2
//1 <= startDayi <= endDayi <= 105

import java.util.*;

// 典型的堆栈题目，每次选结束时间最早的会议
public class MaxEvents {
    public int maxEvents(int[][] events) {
        int minTime = Integer.MAX_VALUE;
        int maxTime = Integer.MIN_VALUE;
        for (int[] event : events) {
            minTime = Integer.min(event[0], minTime);
            maxTime = Integer.max(event[1], maxTime);
        }
        Arrays.sort(events, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o1[1] - o2[1];
            }
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] != o2[1]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        });
        int res = 0;
        int j = 0;
        for (int i = minTime; i <= maxTime; i++) {
            // 先把过期的会议去掉
            while (!pq.isEmpty()) {
                int[] event = pq.peek();
                if (event[1] >= i) {
                    break;
                }
                pq.poll();
            }
            // 把符合要求的会议加入
            while (j < events.length && events[j][0] <= i) {
                pq.offer(events[j]);
                j++;
            }
            // 利用小根堆来获取满足条件的结束条件最早的会议
            if (!pq.isEmpty()) {
                pq.poll();
                res++;
            }
        }
        return res;
    }
}
