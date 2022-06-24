package com.liyongquan.bfs;

import com.liyongquan.tree.TreeNode;

import java.util.*;

/**
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
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
 * [20,9],
 * [15,7]
 * ]
 *  
 * <p>
 * 提示：
 * <p>
 * 节点总数 <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LevelOrderTree2 {
    /**
     * 使用双端队列解决，同时具备队列和栈的特性
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> result = new LinkedList<>();
        int depth = 1;
        while (!queue.isEmpty()) {
            List<Integer> cur = new LinkedList<>();
            int size = queue.size();
            if (depth % 2 == 1) {
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.pollFirst();
                    cur.add(poll.val);
                    if (poll.left != null) {
                        queue.offerLast(poll.left);
                    }
                    if (poll.right != null) {
                        queue.offerLast(poll.right);
                    }
                }
            } else {
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.pollLast();
                    cur.add(poll.val);
                    if (poll.right != null) {
                        queue.offerFirst(poll.right);
                    }
                    if (poll.left != null) {
                        queue.offerFirst(poll.left);
                    }
                }
            }
            depth++;
            result.add(cur);
        }
        return result;
    }
}
