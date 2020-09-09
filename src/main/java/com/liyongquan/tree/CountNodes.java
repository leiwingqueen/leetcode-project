package com.liyongquan.tree;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 * <p>
 * 说明：
 * <p>
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * 1
 * / \
 * 2   3
 * / \  /
 * 4  5 6
 * <p>
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountNodes {
    /**
     * bfs解法，但是没有使用完全二叉树的特性，所以效率肯定不是最优的
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            count++;
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
        return count;
    }

    /**
     * 假设我们找到最右下方结点的位置，就可以确定整个树的结点数量
     * 假设最右下方的结点为第h层，从右往左数第r个位置。
     * 则接待数量为2^0+2^1+...2^h-1-r+1
     *
     * @param root
     * @return
     */
    public int countNodes2(TreeNode root) {
        return 0;
    }
}
