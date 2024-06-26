package com.liyongquan.weeklycontest.bwc129;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinimumTime {
    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        Map<Integer, Integer>[] graph = new Map[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashMap<>();
        }
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int w = edge[2];
            if (!graph[x].containsKey(y) || graph[x].get(y) > w) {
                graph[x].put(y, w);
                graph[y].put(x, w);
            }
        }
        int[] res = new int[n];
        // 已经确定的集合
        boolean[] flag = new boolean[n];
        for (int i = 0; i < n; i++) {
            res[i] = -1;
        }
        res[0] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.add(new int[]{0, 0});
        while (queue.size() > 0) {
            while (queue.size() > 0 && flag[queue.peek()[0]]) {
                queue.poll();
            }
            if (queue.size() == 0) {
                break;
            }
            int[] node = queue.poll();
            flag[node[0]] = true;
            if (disappear[node[0]] <= res[node[0]]) {
                res[node[0]] = -1;
            } else {
                for (Map.Entry<Integer, Integer> entry : graph[node[0]].entrySet()) {
                    Integer k = entry.getKey();
                    Integer w = entry.getValue();
                    if (!flag[k] && (res[k] < 0 || res[node[0]] + w < res[k])) {
                        res[k] = res[node[0]] + w;
                        queue.add(new int[]{k, res[k]});
                    }
                }
            }
        }
        return res;
    }
}
