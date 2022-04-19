package com.liyongquan.weeklycontest.wc289;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

//给你一棵 树（即一个连通、无向、无环图），根节点是节点 0 ，这棵树由编号从 0 到 n - 1 的 n 个节点组成。用下标从 0 开始、长度为 n 的数组 parent 来表示这棵树，其中 parent[i] 是节点 i 的父节点，由于节点 0 是根节点，所以 parent[0] == -1 。
//
//另给你一个字符串 s ，长度也是 n ，其中 s[i] 表示分配给节点 i 的字符。
//
//请你找出路径上任意一对相邻节点都没有分配到相同字符的 最长路径 ，并返回该路径的长度。
//
// 
//
//示例 1：
//
//
//
//输入：parent = [-1,0,0,1,1,2], s = "abacbe"
//输出：3
//解释：任意一对相邻节点字符都不同的最长路径是：0 -> 1 -> 3 。该路径的长度是 3 ，所以返回 3 。
//可以证明不存在满足上述条件且比 3 更长的路径。
//示例 2：
//
//
//
//输入：parent = [-1,0,0,0], s = "aabc"
//输出：3
//解释：任意一对相邻节点字符都不同的最长路径是：2 -> 0 -> 3 。该路径的长度为 3 ，所以返回 3 。
// 
//
//提示：
//
//n == parent.length == s.length
//1 <= n <= 105
//对所有 i >= 1 ，0 <= parent[i] <= n - 1 均成立
//parent[0] == -1
//parent 表示一棵有效的树
//s 仅由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-path-with-different-adjacent-characters
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

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
