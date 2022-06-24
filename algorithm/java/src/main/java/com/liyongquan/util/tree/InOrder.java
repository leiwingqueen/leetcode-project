package com.liyongquan.util.tree;

import com.liyongquan.tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 中序遍历-迭代写法
 */
public class InOrder {
    public List<Integer> inorder(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            } else {
                TreeNode top = stack.pollFirst();
                res.add(top.val);
                if (top.right != null) {
                    cur = top.right;
                }
            }
        }
        return res;
    }
}
