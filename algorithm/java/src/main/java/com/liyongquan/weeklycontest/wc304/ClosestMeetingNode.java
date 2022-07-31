package com.liyongquan.weeklycontest.wc304;

import java.util.*;

public class ClosestMeetingNode {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        if (node1 == node2) {
            return node1;
        }
        List<Integer> p1 = path(edges, node1);
        List<Integer> p2 = path(edges, node2);
        Map<Integer, Integer> map = new HashMap<>();
        int dis = Integer.MAX_VALUE;
        int res = -1;
        for (int i = 0; i < p1.size(); i++) {
            map.put(p1.get(i), i);
        }
        for (int i = 0; i < p2.size(); i++) {
            Integer idx2 = p2.get(i);
            if (map.containsKey(idx2)) {
                int d2 = Math.max(i, map.get(idx2));
                if (d2 < dis || (d2 == dis && idx2 < res)) {
                    dis = d2;
                    res = idx2;
                }
            }
        }
        return res;
    }

    private List<Integer> path(int[] edges, int node1) {
        int len = edges.length;
        boolean[] visit = new boolean[len];
        List<Integer> l = new ArrayList<>();
        while (node1 >= 0 && !visit[node1]) {
            l.add(node1);
            visit[node1] = true;
            node1 = edges[node1];
        }
        return l;
    }

    /**
     * 解法2
     *
     * @param edges
     * @param node1
     * @param node2
     * @return
     */
    public int closestMeetingNode2(int[] edges, int node1, int node2) {
        int n = edges.length;
        int[] d1 = distance(edges, node1);
        int[] d2 = distance(edges, node2);
        int res = -1;
        int dis = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (d1[i] >= 0 && d2[i] >= 0) {
                int d = Math.max(d1[i], d2[i]);
                if (d < dis) {
                    dis = d;
                    res = i;
                }
            }
        }
        return res;
    }

    private int[] distance(int[] edges, int start) {
        int n = edges.length;
        int[] d = new int[n];
        Arrays.fill(d, -1);
        int clock = 0;
        while (start >= 0 && d[start] < 0) {
            d[start] = clock;
            start = edges[start];
            clock++;
        }
        return d;
    }
}
