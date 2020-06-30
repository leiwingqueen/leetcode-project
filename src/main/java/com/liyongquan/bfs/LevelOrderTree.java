package com.liyongquan.bfs;

import com.liyongquan.tree.TreeNode;

import java.util.*;

/**
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * <p>
 *  
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 *  
 * <p>
 * 提示：
 * <p>
 * 节点总数 <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LevelOrderTree {
    /**
     * 使用一个辅助的depth队列用于维护当前结点的深度。
     * BFS
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Queue<Integer> depth = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        depth.offer(1);
        queue.offer(root);
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> cur = new LinkedList<>();
        int curDepth = 1;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            Integer dep = depth.poll();
            if (curDepth == dep.intValue()) {
                cur.add(poll.val);
            } else {
                result.add(cur);
                cur = new LinkedList<>();
                cur.add(poll.val);
                curDepth++;
            }
            if (poll.left != null) {
                queue.offer(poll.left);
                depth.offer(dep + 1);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
                depth.offer(dep + 1);
            }
        }
        result.add(cur);
        return result;
    }
}
