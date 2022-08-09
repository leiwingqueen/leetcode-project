package com.liyongquan.weeklycontest.wc305;

import java.util.*;

public class ReachableNodes {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph[from].add(to);
            graph[to].add(from);
        }
        Set<Integer> restrictSet = new HashSet<>();
        for (int i : restricted) {
            restrictSet.add(i);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        boolean[] visit = new boolean[n];
        visit[0] = true;
        int res = 1;
        while (queue.size() > 0) {
            Integer poll = queue.poll();
            for (Integer next : graph[poll]) {
                if (!visit[next] && !restrictSet.contains(next.intValue())) {
                    res++;
                    visit[next] = true;
                    queue.add(next);
                }
            }
        }
        return res;
    }
}
