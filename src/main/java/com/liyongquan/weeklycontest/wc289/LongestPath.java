package com.liyongquan.weeklycontest.wc289;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class LongestPath {
    private int[] depth;
    private int res;

    public int longestPath(int[] parent, String s) {
        int len = parent.length;
        //构造树
        LPNode[] nodes = new LPNode[len];
        for (int i = 0; i < len; i++) {
            nodes[i] = new LPNode(i, s.charAt(i));
        }
        LPNode root = null;
        for (int i = 0; i < len; i++) {
            if (parent[i] == -1) {
                root = nodes[i];
            } else {
                nodes[parent[i]].add(nodes[i]);
            }
        }
        //保存每个结点的最大深度
        depth = new int[len];
        dfs(root);
        dfs2(root);
        return res;
    }

    private int dfs(LPNode root) {
        if (root == null) {
            return 0;
        }
        int mx = 0;
        for (LPNode child : root.children) {
            dfs(child);
            if (child.ch != root.ch) {
                mx = Math.max(depth[child.idx], mx);
            }
        }
        depth[root.idx] = mx + 1;
        return mx + 1;
    }

    private void dfs2(LPNode root) {
        if (root == null) {
            return;
        }
        //经过root结点的最长路径，只保留最长的两条路径
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (LPNode child : root.children) {
            dfs2(child);
            if (child.ch != root.ch) {
                pq.add(depth[child.idx]);
                if (pq.size() > 2) {
                    pq.poll();
                }
            }
        }
        int sum = 1;
        while (pq.size() > 0) {
            sum += pq.poll();
        }
        res = Math.max(res, sum);
    }

    private static class LPNode {
        char ch;
        int idx;
        List<LPNode> children;

        public LPNode(int idx, char ch) {
            this.ch = ch;
            this.idx = idx;
            children = new LinkedList<>();
        }

        public void add(LPNode node) {
            children.add(node);
        }
    }
}
