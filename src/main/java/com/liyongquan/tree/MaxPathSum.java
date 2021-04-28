package com.liyongquan.tree;

import com.liyongquan.dfs.IntegerReplacement;
import lombok.extern.slf4j.Slf4j;

/**
 * 124. 二叉树中的最大路径和
 * <p>
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class MaxPathSum {
    /**
     * 递归解法，最终简化成选和不选的问题
     * <p>
     * 不通过
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    /**
     * @param root
     * @return [不包含root的最大路径和，左子树的最大路径和，右子树的最大路径和]
     */
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
        }
        //叶子节点
        if (root.left == null && root.right == null) {
            return new int[]{Integer.MIN_VALUE, root.val};
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int r0 = Math.max(left[0], Math.max(left[1], Math.max(right[0], right[1])));
        int r1 = root.val;
        if (left[1] > 0) {
            r1 += left[1];
        }
        if (right[1] > 0) {
            r1 += right[1];
        }
        log.info("root:{},r0:{},r1:{}", root.val, r0, r1);
        return new int[]{r0, r1};
    }

    private int max = Integer.MIN_VALUE;

    public int maxPathSum2(TreeNode root) {
        maxGain(root);
        return max;
    }

    private int maxGain(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(maxGain(root.left), 0);
        int right = Math.max(maxGain(root.right), 0);
        max = Math.max(max, left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}
