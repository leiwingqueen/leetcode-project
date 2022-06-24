package com.liyongquan.tree;

//给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
//
//
//
// 示例 1：
//
//
//输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
//输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
//
//
// 示例 2：
//
//
//输入：root = [5,1,7]
//输出：[1,null,5,null,7]
//
//
//
//
// 提示：
//
//
// 树中节点数的取值范围是 [1, 100]
// 0 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 递归
// 👍 173 👎 0

public class IncreasingBST {
    /**
     * 递归解法
     *
     * @param root
     * @return
     */
    public TreeNode increasingBST(TreeNode root) {
        return dfs(root)[0];
    }

    private TreeNode[] dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return new TreeNode[]{root, root};
        }
        TreeNode nr = root, last = root;
        if (root.left != null) {
            TreeNode[] left = dfs(root.left);
            nr = left[0];
            left[1].right = root;
        }
        root.left = null;
        if (root.right != null) {
            TreeNode[] right = dfs(root.right);
            root.right = right[0];
            last = right[1];
        }
        return new TreeNode[]{nr, last};
    }
}
