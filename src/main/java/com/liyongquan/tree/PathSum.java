package com.liyongquan.tree;


/**
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * <p>
 * 找出路径和等于给定数值的路径总数。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * <p>
 * 示例：
 * <p>
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * <p>
 * 10
 * /  \
 * 5   -3
 * / \    \
 * 3   2   11
 * / \   \
 * 3  -2   1
 * <p>
 * 返回 3。和等于 8 的路径有:
 * <p>
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PathSum {
    /**
     * 双重dfs，其实性能会很糟糕
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int count = dfs(root, sum);
        if (root.left != null) {
            count += pathSum(root.left, sum);
        }
        if (root.right != null) {
            count += pathSum(root.right, sum);
        }
        return count;
    }

    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int count = root.val == sum ? 1 : 0;
        if (root.left != null) {
            count += dfs(root.left, sum - root.val);
        }
        if (root.right != null) {
            count += dfs(root.right, sum - root.val);
        }
        //System.out.println("节点:"+root.val+"的路径和为:"+ count);
        return count;
    }
}
