package com.liyongquan.tree;

import java.util.*;

public class LevelOrder {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> l = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                Node poll = queue.poll();
                l.add(poll.val);
                for (Node child : poll.children) {
                    queue.add(child);
                }
            }
            res.add(l);
        }
        return res;
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
