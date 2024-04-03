package com.liyongquan.tree;

public class GetTargetCopy {
    private TreeNode search(TreeNode node, TreeNode target) {
        if (node == null) {
            return null;
        }
        if (node.val == target.val) {
            return node;
        }
        TreeNode left = search(node.left, target);
        if (left != null) {
            return left;
        }
        return search(node.right, target);
    }

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        return search(cloned, target);
    }
}
