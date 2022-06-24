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
 *  
 * <p>
 * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Bst2GreaterTree {
    /**
     * 典型的递归，BST的父节点和右子节点均大于当前节点，因此我们计算当前节点的值需要先计算这两个节点
     * f(node)=f(node.parent)+f(node.right)
     * <p>
     * 由于父节点不能直接获取，我们需要通过递归传参实现
     *
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        return dfs(root, 0);
    }

    private TreeNode dfs(TreeNode node, int parent) {
        if (node == null) {
            return null;
        }
        int sum = 0;
        sum += parent;
        if (node.right != null) {
            sum += sum(node.right);
            dfs(node.right, parent);
        }
        node.val += sum;
        if (node.left != null) {
            dfs(node.left, node.val);
        }
        return node;
    }

    private int sum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sum = node.val;
        if (node.left != null) {
            sum += sum(node.left);
        }
        if (node.right != null) {
            sum += sum(node.right);
        }
        return sum;
    }

    /**
     * 反序-中序遍历
     *
     * @param root
     * @return
     */
    int sum = 0;

    public TreeNode convertBST2(TreeNode root) {
        if (root != null) {
            convertBST2(root.right);
            sum += root.val;
            root.val = sum;
            convertBST2(root.left);
        }
        return root;
    }

    public TreeNode convertBST3(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    private int dfs2(TreeNode root, int parentVal) {
        if (root == null)
            return parentVal;
        root.val += dfs2(root.right, parentVal);
        return dfs2(root.left, root.val);
    }
}
