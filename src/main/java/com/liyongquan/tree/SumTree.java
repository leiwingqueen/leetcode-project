package com.liyongquan.tree;

import com.liyongquan.linklist.IntersectionNode;

import javax.management.QueryEval;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 * <p>
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 * <p>
 * 以 10^9 + 7 为模，返回这些数字之和。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：[1,0,1,0,1,0,1]
 * 输出：22
 * 解释：(100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *  
 * <p>
 * 提示：
 * <p>
 * 树中的结点数介于 1 和 1000 之间。
 * node.val 为 0 或 1 。
 * 通过次数6,076提交次数9,885
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-root-to-leaf-binary-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SumTree {
    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int sum) {
        sum = sum * 2 + node.val;
        //叶子结点
        if (node.left == null && node.right == null) {
            return sum;
        }
        int result = 0;
        if (node.left != null) {
            result += dfs(node.left, sum);
        }
        if (node.right != null) {
            result += dfs(node.right, sum);
        }
        return result;
    }

    public static void main(String[] args) {

    }

    private TreeNode buildTree(String[] array) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(new TreeNode(Integer.valueOf(array[0])));

    }
}
