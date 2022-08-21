package com.liyongquan.weeklycontest.wc307;

import com.liyongquan.tree.TreeNode;

import java.util.*;

public class AmountOfTime {
    private Map<Integer, TreeNode> parentMap = new HashMap<>();
    private Map<Integer, TreeNode> nodeMap = new HashMap<>();

    public int amountOfTime(TreeNode root, int start) {
        dfs(root);
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(nodeMap.get(start));
        Set<Integer> visit = new HashSet<>();
        visit.add(start);
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                List<TreeNode> list = new ArrayList<>();
                if (poll.left != null) {
                    list.add(poll.left);
                }
                if (poll.right != null) {
                    list.add(poll.right);
                }
                if (parentMap.get(poll.val) != null) {
                    list.add(parentMap.get(poll.val));
                }
                for (TreeNode next : list) {
                    if (!visit.contains(next.val)) {
                        queue.add(next);
                        visit.add(next.val);
                    }
                }
            }
            depth++;
        }
        return depth - 1;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        nodeMap.put(root.val, root);
        if (root.left != null) {
            parentMap.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parentMap.put(root.right.val, root);
            dfs(root.right);
        }
    }


}
