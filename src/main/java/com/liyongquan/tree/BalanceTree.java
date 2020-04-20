package com.liyongquan.tree;

/**
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。
 * 如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 返回 true 。
 * <p>
 * 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * <p>
 * 返回 false 。
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= 树的结点个数 <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BalanceTree {
    /**
     * DFS得到左右子树的深度，然后进行比较。
     * 由于树的结点没有一个depth的字段，我们不能通过栈实现。直接使用递归的栈空间来保存当前深度
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return dfs(root, 1).balance;
    }

    private BalanceResult dfs(TreeNode node, int depth) {
        if (node == null) {
            return BalanceResult.of(depth - 1, true);
        }
        BalanceResult left = dfs(node.left, depth + 1);
        if (!left.balance) {
            return BalanceResult.of(0, false);
        }
        BalanceResult right = dfs(node.right, depth + 1);
        if (!right.balance) {
            return BalanceResult.of(0, false);
        }
        if (Math.abs(left.depth - right.depth) <= 1) {
            return BalanceResult.of(Math.max(left.depth, right.depth), true);
        }
        return BalanceResult.of(0, false);
    }

    private static class BalanceResult {
        int depth;
        boolean balance;

        public static BalanceResult of(int depth, boolean balance) {
            BalanceResult result = new BalanceResult();
            result.depth = depth;
            result.balance = balance;
            return result;
        }
    }
}
