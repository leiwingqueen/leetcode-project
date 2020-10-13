package com.liyongquan.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
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
        //叶子节点
        if (root.left == null && root.right == null) {
            return -1;
        }
        int result = -1;
        //假设其中一个节点为根节点，我们需要找到左子树最大的节点和右子树最小的节点
        if (root.left != null) {
            TreeNode l = root.left;
            while (l.right != null) {
                l = l.right;
            }
            result = root.val - l.val;
            //剪枝
            if (result <= 1) {
                return result;
            }
        }
        if (root.right != null) {
            TreeNode r = root.right;
            while (r.left != null) {
                r = r.left;
            }
            result = result == -1 ? (r.val - root.val) : Math.min(r.val - root.val, result);
            //剪枝
            if (result <= 1) {
                return result;
            }
        }
        //递归左右子树
        int left = getMinimumDifference(root.left);
        if (left >= 0 && left <= 1) {
            return left;
        }
        if (left != -1) {
            result = Math.min(result, left);
        }
        int right = getMinimumDifference(root.right);
        if (right >= 0 && right <= 1) {
            return right;
        }
        if (right != -1) {
            result = Math.min(result, right);
        }
        return result;
    }

    /**
     * 二叉搜索树还有一个特性，中序遍历得到的数组是自增的
     *
     * @param root
     * @return
     */
    public int getMinimumDifference3(TreeNode root) {
        if (root == null) {
            return -1;
        }
        List<Integer> list = new LinkedList<>();
        dfs2(root, list);
        int result = -1;
        Iterator<Integer> iterator = list.iterator();
        Integer pre = iterator.next();
        while (iterator.hasNext()) {
            Integer item = iterator.next();
            if (result == -1) {
                result = item - pre;
            } else {
                result = Math.min(result, item - pre);
            }
            pre = item;
        }
        return result;
    }

    private void dfs2(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        dfs2(root.left, list);
        list.add(root.val);
        dfs2(root.right, list);
    }
}
