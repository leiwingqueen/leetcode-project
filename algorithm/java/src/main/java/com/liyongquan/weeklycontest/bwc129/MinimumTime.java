package com.liyongquan.weeklycontest.bwc129;

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
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            res[i] = -1;
        }
        res[0] = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            if (res[o1] < 0 && res[o2] < 0) {
                return 0;
            } else if (res[o1] < 0) {
                return 1;
            } else if (res[o2] < 0) {
                return -1;
            } else {
                return res[o1] - res[o2];
            }
        });
        for (int i = 0; i < n; i++) {
            queue.add(i);
        }
        while (queue.size() > 0) {
            Integer node = queue.poll();
            visited[node] = true;
            if (disappear[node] <= res[node]) {
                res[node] = -1;
            } else {
                for (Map.Entry<Integer, Integer> entry : graph[node].entrySet()) {
                    Integer k = entry.getKey();
                    Integer w = entry.getValue();
                    if (!visited[k] && (res[k] < 0 || res[node] + w < res[k])) {
                        res[k] = res[node] + w;
                    }
                }
            }
        }
        return res;
    }
}
