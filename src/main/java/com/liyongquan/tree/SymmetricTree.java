package com.liyongquan.tree;

/**
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 *
 * 示例 1：
 *
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        TreeNode mirrorTree = mirrorTree(root);
        return equals(root,mirrorTree);
    }
    public TreeNode mirrorTree(TreeNode root) {
        if (root==null) {
            return null;
        }
        TreeNode left = mirrorTree(root.left);
        TreeNode right= mirrorTree(root.right);
        TreeNode node=new TreeNode(root.val);
        node.left=right;
        node.right=left;
        return node;
    }

    public boolean equals(TreeNode A,TreeNode B){
        if (A==null&&B==null) {
            return true;
        }
        if (A==null) {
            return false;
        }
        if (B==null) {
            return false;
        }
        if (A.val!=B.val) {
            return false;
        }
        if (!equals(A.left,B.left)||!equals(A.right,B.right)) {
            return false;
        }
        return true;
    }
}
