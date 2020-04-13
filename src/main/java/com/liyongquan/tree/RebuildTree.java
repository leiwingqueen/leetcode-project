package com.liyongquan.tree;

/**
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 */
public class RebuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

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
        /**
         * 前序遍历 preorder = [3,9,20,15,7]
         * 中序遍历 inorder = [9,3,15,20,7]
         *
         *     3
         *    / \
         *   9  20
         *     /  \
         *    15   7
         */
        int[] preorder=new int[]{3,9,20,15,7};
        int[] inorder=new int[]{9,3,15,20,7};
        RebuildTree tree=new RebuildTree();
        tree.buildTree(preorder,inorder);
    }
}
