package com.liyongquan.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * <p>
 * 1
 * \
 * 3
 * /
 * 2
 * <p>
 * 输出：
 * 1
 * <p>
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinAbs {
    /**
     * 没有利用二叉搜索数的特点
     * 构造list的复杂度为O(n),然后比较任意两个数的复杂度为O(n*(n-1)/2)
     *
     * @param root
     * @return
     */
    public int getMinimumDifference1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        int min = -1;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int abs = Math.abs(list.get(i) - list.get(j));
                if (min < 0 || abs < min) {
                    min = abs;
                }
            }
        }
        return min;
    }

    private void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        dfs(node.left, list);
        dfs(node.right, list);
    }

    /**
     * 利用二叉搜索树的特点，左子树小于父结点，右子树大于父结点。
     *
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int min = -1;
        if (root.left != null) {
            int left = getMinimumDifference(root.left);
            if (left >= 0 && (min < 0 || left < min)) {
                min = left;
            }
            int i = maxDfs(root.left);
            if (min < 0 || root.val - i < min) {
                min = root.val - i;
            }
        }

        if (root.right != null) {
            int right = getMinimumDifference(root.right);
            if (right >= 0 && (min < 0 || right < min)) {
                min = right;
            }
            int j = minDfs(root.right);
            if (min < 0 || j - root.val < min) {
                min = j - root.val;
            }
        }
        return min;
    }

    private int minDfs(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int i = minDfs(node.left);
        if (i >= 0) {
            return i;
        }
        return node.val;
    }

    private int maxDfs(TreeNode node) {
        if (node == null) {
            return -1;
        }
        int i = minDfs(node.right);
        if (i >= 0) {
            return i;
        }
        return node.val;
    }
}
