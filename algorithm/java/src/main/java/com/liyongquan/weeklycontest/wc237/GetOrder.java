package com.liyongquan.weeklycontest.wc237;

import java.util.*;

public class GetOrder {

    private static class Pair {
        int key;
        int[] value;
        Pair(int key, int[] value) {
            this.key = key;
            this.value = value;
        }
    }
    /**
     * 时间复杂度O(nlog(n))
     *
     * @param tasks
     * @return
     */
    public int[] getOrder(int[][] tasks) {
        int len = tasks.length;
        Pair[] arr = new Pair[len];
        for (int i = 0; i < len; i++) {
            arr[i] = new Pair(i, tasks[i]);
        }
        Arrays.sort(arr, (o1, o2) -> {
            if (o1.value[0] != o2.value[0]) {
                return o1.value[0] - o2.value[0];
            }
            return o1.key - o2.key;
        });
        int t = 1;
        int p1 = 0, p2 = 0;
        int[] res = new int[len];
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o1.value[1] != o2.value[1] ? o1.value[1] - o2.value[1] : o1.key - o2.key);
        while (p1 < len) {
            //找到满足开始条件的任务
            while (p2 < len && arr[p2].value[0] <= t) {
                pq.add(arr[p2++]);
            }
            //找不到,空闲等待
            if (pq.isEmpty()) {
                t = arr[p2].value[0];
                continue;
            }
            //执行任务最短的job
            Pair poll = pq.poll();
            res[p1++] = poll.key;
            //时间更新
            t += poll.value[1];
        }
        return res;
    }
}
