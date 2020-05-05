package com.liyongquan.tree;

import java.util.LinkedList;
import java.util.Queue;

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

    private int dfs(TreeNode node, int value) {
        if (node == null) {
            return 0;
        }
        int sum = 0;
        if (node.right != null) {
            int rightSum = dfs(node.right, value);
            node.val += rightSum;
            sum += rightSum;
        } else {
            node.val += value;
        }
        sum += node.val;
        if (node.left != null) {
            int leftSum = dfs(node.left, node.val);
            sum += leftSum;
        }
        return sum;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(2);
        TreeNode left = new TreeNode(0);
        TreeNode right = new TreeNode(3);
        head.left = left;
        head.right = right;
        left.left = new TreeNode(-4);
        left.right = new TreeNode(1);
        GreaterTree tree = new GreaterTree();
        tree.convertBST(head);
        print(head);
    }

    private static void print(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            System.out.println(poll.val);
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
    }
}
