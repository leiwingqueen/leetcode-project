package com.liyongquan.tree;

import com.liyongquan.dp.Rope;

import java.time.temporal.ValueRange;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
 * <p>
 * 示例 1:
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * 输入:
 * 5
 * / \
 * 1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/legal-binary-search-tree-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class JudgeSearchTree {
    public boolean isValidBST(TreeNode root) {
        return dfs(root).valid;
    }

    private MaxMinResult dfs(TreeNode node) {
        if (node == null) {
            return new MaxMinResult(true);
        }
        int max, min;
        max = min = node.val;
        if (node.left != null) {
            MaxMinResult left = dfs(node.left);
            if (!left.valid || left.max >= node.val) {
                return new MaxMinResult(false);
            }
            min = left.min;
        }
        if (node.right != null) {
            MaxMinResult right = dfs(node.right);
            if (!right.valid || right.min <= node.val) {
                return new MaxMinResult(false);
            }
            max = right.max;
        }
        return new MaxMinResult(true, max, min);
    }

    private static class MaxMinResult {
        boolean valid;
        int max;
        int min;

        public MaxMinResult(boolean valid, int max, int min) {
            this.valid = valid;
            this.max = max;
            this.min = min;
        }

        public MaxMinResult(boolean valid) {
            this.valid = valid;
        }
    }

    /**
     * 递归写法2
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * @param root
     * @param lower 下界
     * @param upper 上界
     * @return
     */
    private boolean isValid(TreeNode root, long lower, long upper) {
        if (root == null) {
            return true;
        }
        if (root.val < lower || root.val > upper) {
            return false;
        }
        return isValid(root.left, lower, (long) root.val - 1) && isValid(root.right, (long) root.val + 1, upper);
    }

    /**
     * 中序遍历-递归解法
     * <p>
     * 中序遍历得到的一定是升序
     *
     * @param root
     * @return
     */
    public boolean isValidBST3(TreeNode root) {
        List<Integer> list = inorder(root);
        long pre = Long.MIN_VALUE;
        for (Integer item : list) {
            if (item.longValue() <= pre) {
                return false;
            }
            pre = item.longValue();
        }
        return true;
    }

    private List<Integer> inorder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<Integer> res = inorder(root.left);
        res.add(root.val);
        res.addAll(inorder(root.right));
        return res;
    }

    /**
     * 中序遍历非递归写法
     *
     * @param root
     * @return
     */
    public boolean isValidBST4(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.offerFirst(cur);
                cur = cur.left;
            } else {
                TreeNode top = stack.pollFirst();
                res.add(top.val);
                if (top.right != null) {
                    cur = top.right;
                }
            }
        }
        //再做一次遍历
        long pre = Long.MIN_VALUE;
        for (Integer item : res) {
            if (item.longValue() <= pre) {
                return false;
            }
            pre = item.longValue();
        }
        return true;
    }


}
