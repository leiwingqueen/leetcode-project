package com.liyongquan.tree;

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/7/28
 */
public class DistanceK {
    /**
     * 直观感觉用bfs可以解决，但是树缺少上一层结点的指针，我们需要自己构造
     *
     * @param root
     * @param target
     * @param k
     * @return
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> prev = new HashMap<>();
        makePrev(root, prev);
        //bfs找到该层的节点
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visit = new HashSet<>();
        visit.add(target);
        queue.add(target);
        int depth = 0;
        List<Integer> res = new LinkedList<>();
        while (!queue.isEmpty() && depth <= k) {
            if (depth == k) {
                while (!queue.isEmpty()) {
                    res.add(queue.poll().val);
                }
                return res;
            }
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null && !visit.contains(node.left)) {
                    queue.add(node.left);
                    visit.add(node.left);
                }
                if (node.right != null && !visit.contains(node.right)) {
                    queue.add(node.right);
                    visit.add(node.right);
                }
                TreeNode parent = prev.get(node);
                if (parent != null && !visit.contains(parent)) {
                    queue.add(parent);
                    visit.add(parent);
                }
            }
            depth++;
        }
        return res;
    }

    private void makePrev(TreeNode root, Map<TreeNode, TreeNode> prev) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            prev.put(root.left, root);
            makePrev(root.left, prev);
        }
        if (root.right != null) {
            prev.put(root.right, root);
            makePrev(root.right, prev);
        }
    }
}
