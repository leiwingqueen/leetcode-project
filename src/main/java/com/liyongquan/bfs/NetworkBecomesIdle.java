package com.liyongquan.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NetworkBecomesIdle {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int e1 = edge[0];
            int e2 = edge[1];
            graph[e1].add(e2);
            graph[e2].add(e1);
        }
        //bfs计算最短路径
        int[] dis = new int[n];
        boolean[] visit = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visit[0] = true;
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer node = queue.poll();
                dis[node] = depth;
                for (Integer next : graph[node]) {
                    if (!visit[next]) {
                        visit[next] = true;
                        queue.add(next);
                    }
                }
            }
            depth++;
        }
        //计算最后一个包的发送时间
        int max = 0;
        for (int i = 1; i < n; i++) {
            int d = 2 * dis[i];
            int p = patience[i];
            int t = d + 1;
            if ((d - 1) / p > 0) {
                //还需要额外发包
                t = (d - 1) / p * p + d + 1;
            }
            max = Math.max(max, t);
        }
        return max;
    }
}
