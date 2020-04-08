package com.liyongquan.tree;

public class BinaryTree2 {
    public BinaryTree.TreeNode lowestCommonAncestor(BinaryTree.TreeNode root, BinaryTree.TreeNode p, BinaryTree.TreeNode q) {
        BinaryTree.TreeNode cur=root;
        return cur;
    }
    private BinaryTree.TreeNode find(BinaryTree.TreeNode root, BinaryTree.TreeNode p, BinaryTree.TreeNode q){
        if (root==null) {
            return null;
        }

        BinaryTree.TreeNode left = find(root.left, p, q);
        BinaryTree.TreeNode right= find(root.right, p, q);
        if (left==null&&right==null) {
            return root;
        }
        return left==null?right:left;
    }
    public class TreeNode {
        int val;
        BinaryTree.TreeNode left;
        BinaryTree.TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}
