package com.liyongquan.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [10,5,15,3,7,null,18], low = 7, high = 15
 * 输出：32
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * 输出：23
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [1, 2 * 104] 内
 * 1 <= Node.val <= 105
 * 1 <= low <= high <= 105
 * 所有 Node.val 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-of-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RangeSumBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null || low > high) {
            return 0;
        }
        if (root.val <= low) {
            int sum = 0;
            if (root.val == low) {
                sum += root.val;
            }
            return sum + rangeSumBST(root.right, low, high);
        } else if (root.val >= high) {
            int sum = 0;
            if (root.val == high) {
                sum += root.val;
            }
            return sum + rangeSumBST(root.left, low, high);
        } else {
            return root.val + rangeSumBST(root.left, low, root.val - 1) + rangeSumBST(root.right, root.val + 1, high);
        }
    }

    /**
     * 上面基础稍微优化一下
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST2(TreeNode root, int low, int high) {
        if (root == null || low > high) {
            return 0;
        }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        } else if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        } else {
            return root.val + rangeSumBST(root.left, low, root.val - 1) + rangeSumBST(root.right, root.val + 1, high);
        }
    }

    /**
     * bfs
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public int rangeSumBST3(TreeNode root, int low, int high) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.val < low) {
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            } else if (poll.val > high) {
                if (poll.left != null) {
                    queue.add(poll.left);
                }
            } else {
                sum += poll.val;
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return sum;
    }
}
