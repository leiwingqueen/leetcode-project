package com.liyongquan.weeklycontest.wc237;

import javafx.util.Pair;

import java.util.*;

public class GetOrder {
    /**
     * 时间复杂度O(nlog(n))
     *
     * @param tasks
     * @return
     */
    public int[] getOrder(int[][] tasks) {
        int len = tasks.length;
        Pair<Integer, int[]>[] arr = new Pair[len];
        for (int i = 0; i < len; i++) {
            arr[i] = new Pair<>(i, tasks[i]);
        }
        Arrays.sort(arr, (o1, o2) -> {
            if (o1.getValue()[0] != o2.getValue()[0]) {
                return o1.getValue()[0] - o2.getValue()[0];
            }
            return o1.getKey() - o2.getKey();
        });
        int t = 1;
        int p1 = 0, p2 = 0;
        int[] res = new int[len];
        PriorityQueue<Pair<Integer, int[]>> pq = new PriorityQueue<>((o1, o2) -> o1.getValue()[1] != o2.getValue()[1] ? o1.getValue()[1] - o2.getValue()[1] : o1.getKey() - o2.getKey());
        while (p1 < len) {
            //找到满足开始条件的任务
            while (p2 < len && arr[p2].getValue()[0] <= t) {
                pq.add(arr[p2++]);
            }
            //找不到,空闲等待
            if (pq.isEmpty()) {
                t = arr[p2].getValue()[0];
                continue;
            }
            //执行任务最短的job
            Pair<Integer, int[]> poll = pq.poll();
            res[p1++] = poll.getKey();
            //时间更新
            t += poll.getValue()[1];
        }
        return res;
    }
}
