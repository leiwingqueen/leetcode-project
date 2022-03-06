package com.liyongquan.weeklycontest.wc283;

import com.liyongquan.tree.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CreateBinaryTree {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> child = new HashSet<>();
        for (int[] desc : descriptions) {
            int parent = desc[0];
            int cur = desc[1];
            int l = desc[2];
            if (!map.containsKey(parent)) {
                map.put(parent, new TreeNode(parent));
            }
            if (!map.containsKey(cur)) {
                TreeNode node = new TreeNode(cur);
                map.put(cur, node);
            }
            child.add(cur);
            if (l == 1) {
                map.get(parent).left = map.get(cur);
            } else {
                map.get(parent).right = map.get(cur);
            }
        }
        //扫描父节点
        for (Map.Entry<Integer, TreeNode> entry : map.entrySet()) {
            if (!child.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
