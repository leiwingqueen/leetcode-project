package com.liyongquan.graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

// 迪克斯屈拉模板
public class DijkstraTemplate {
    public int[] minimumTime(int n, int[][] edges, int start) {
        Map<Integer, Integer>[] graph = new Map[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashMap<>();
        }
        for (int[] edge : edges) {
            int x = edge[0];
            int y = edge[1];
            int w = edge[2];
            // 重复路径处理，只需要保留最后一条
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
        res[start] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.add(new int[]{start, 0});
        while (queue.size() > 0) {
            while (queue.size() > 0 && flag[queue.peek()[0]]) {
                queue.poll();
            }
            if (queue.size() == 0) {
                break;
            }
            int[] node = queue.poll();
            flag[node[0]] = true;
            for (Map.Entry<Integer, Integer> entry : graph[node[0]].entrySet()) {
                Integer k = entry.getKey();
                Integer w = entry.getValue();
                if (!flag[k] && (res[k] < 0 || res[node[0]] + w < res[k])) {
                    res[k] = res[node[0]] + w;
                    queue.add(new int[]{k, res[k]});
                }
            }
        }
        return res;
    }
}
