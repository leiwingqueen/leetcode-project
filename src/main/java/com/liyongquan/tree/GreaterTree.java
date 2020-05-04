package com.liyongquan.tree;

/**
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * <p>
 *  
 * <p>
 * 例如：
 * <p>
 * 输入: 原始二叉搜索树:
 * 5
 * /   \
 * 2     13
 * <p>
 * 输出: 转换为累加树:
 * 18
 * /   \
 * 20     13
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GreaterTree {
    /**
     * 中序遍历，先处理右子树，再处理当前结点，最后再处理左子树
     * dfs
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    private void dfs(TreeNode root, int rightValue) {
        if (root == null) {
            return;
        }
        if (root.right != null) {
            dfs(root.right, rightValue);
            root.val += root.right.val;
        }
        if (root.left != null) {
            dfs(root.left, rightValue + root.val);
        }
        root.val += rightValue;
    }

    public static void main(String[] args) {
        TreeNode head=new TreeNode(5);
        TreeNode left=new TreeNode(2);
        TreeNode right=new TreeNode(13);
        head.left=left;
        head.right=right;
        GreaterTree tree=new GreaterTree();
        tree.convertBST(head);
    }
}
