package com.liyongquan.tree;


import java.util.HashMap;
import java.util.Map;

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
     * <p>
     * 时间复杂度O(n^2)
     *
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

    /**
     * 有没办法时间复杂度可以降为O(n)呢
     * <p>
     * 解题思路
     * 这道题用到了一个概念，叫前缀和。就是到达当前元素的路径上，之前所有元素的和。
     * <p>
     * 前缀和怎么应用呢？
     * <p>
     * 如果两个数的前缀总和是相同的，那么这些节点之间的元素总和为零。进一步扩展相同的想法，如果前缀总和currSum，在节点A和节点B处相差target，则位于节点A和节点B之间的元素之和是target。
     * <p>
     * 因为本题中的路径是一棵树，从根往任一节点的路径上(不走回头路)，有且仅有一条路径，因为不存在环。(如果存在环，前缀和就不能用了，需要改造算法)
     * <p>
     * 抵达当前节点(即B节点)后，将前缀和累加，然后查找在前缀和上，有没有前缀和currSum-target的节点(即A节点)，存在即表示从A到B有一条路径之和满足条件的情况。结果加上满足前缀和currSum-target的节点的数量。然后递归进入左右子树。
     * <p>
     * 左右子树遍历完成之后，回到当前层，需要把当前节点添加的前缀和去除。避免回溯之后影响上一层。因为思想是前缀和，不属于前缀的，我们就要去掉它。
     * <p>
     * 作者：burning-summer
     * 链接：https://leetcode-cn.com/problems/paths-with-sum-lcci/solution/qian-zhui-he-de-ying-yong-di-gui-hui-su-by-shi-huo/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int pathSum2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0, 1);
        return recursionPathSum(root, 0, sum, preSumMap);
    }


    /**
     * 回溯+前缀和
     * 时间复杂度O(n)
     *
     * @param node
     * @param curSum
     * @param preSumMap
     * @return
     */
    private int recursionPathSum(TreeNode node, int curSum, int target, Map<Integer, Integer> preSumMap) {
        if (node == null) {
            return 0;
        }
        curSum += node.val;
        int result = 0;
        //截止到当前节点的前缀和
        result += preSumMap.getOrDefault(curSum - target, 0);
        preSumMap.put(curSum, preSumMap.getOrDefault(curSum, 0) + 1);
        //左子树
        if (node.left != null) {
            result += recursionPathSum(node.left, curSum, target, preSumMap);
        }
        if (node.right != null) {
            result += recursionPathSum(node.right, curSum, target, preSumMap);
        }
        //回溯，还原preSumMap
        preSumMap.put(curSum, preSumMap.getOrDefault(curSum, 0) - 1);
        return result;
    }
}
