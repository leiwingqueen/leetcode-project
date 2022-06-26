package com.liyongquan.weeklycontest.wc299;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinimumScore {
    int res = Integer.MAX_VALUE;
    int[] xor;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int e0 = edge[0];
            int e1 = edge[1];
            graph[e0].add(e1);
            graph[e1].add(e0);
        }
        //bfs构建树
        Node root = new Node(0);
        boolean[] visit = new boolean[n];
        visit[0] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Integer next : graph[node.val]) {
                if (!visit[next]) {
                    Node childNode = new Node(next);
                    node.child.add(childNode);
                    queue.add(childNode);
                    visit[next] = true;
                }
            }
        }
        //dfs扫描树的xor值
        xor = new int[n];
        dfs1(root, nums);
        return res;
    }

    private int dfs1(Node root, int[] nums) {
        if (root == null) {
            return 0;
        }
        int x = 0;
        for (Node node : root.child) {
            x ^= dfs1(node, nums);
        }
        xor[root.val] = nums[root.val] ^ x;
        return xor[root.val];
    }

    private void dfs2(Node root) {
        //TODO:待完成
        return;
    }

    private static class Node {
        int val;
        List<Node> child;

        public Node(int val) {
            this.val = val;
            this.child = new ArrayList<>();
        }
    }
}
