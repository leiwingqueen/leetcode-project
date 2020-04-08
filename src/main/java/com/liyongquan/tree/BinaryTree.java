package com.liyongquan.tree;

import java.util.Currency;

public class BinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur=root;
        //p,q位于cur的两边，则证明我们已经找到了最小的父结点
        while ((p.val<cur.val&&q.val<cur.val)||(p.val>cur.val&&q.val>cur.val)){
            boolean left=p.val<cur.val;
            cur=left?cur.left:cur.right;
        }
        return cur;
    }
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }
}
