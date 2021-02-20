package com.liyongquan.tree;


public class BinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;
        //p,q位于cur的两边，则证明我们已经找到了最小的父结点
        while ((p.val < cur.val && q.val < cur.val) || (p.val > cur.val && q.val > cur.val)) {
            boolean left = p.val < cur.val;
            cur = left ? cur.left : cur.right;
        }
        return cur;
    }
}
