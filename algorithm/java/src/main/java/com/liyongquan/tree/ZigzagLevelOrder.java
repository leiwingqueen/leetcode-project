package com.liyongquan.tree;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层序遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ZigzagLevelOrder {
    /**
     * bfs。
     * <p>
     * 唯一区别是正常的bfs使用队列，我们这里使用栈来解决。
     * 奇数层在push栈的时候先push左子树，再push右子树。偶数层反之。
     * <p>
     * 时间复杂度O(n),空间复杂度O(k),k是一层的最大大小
     *
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        boolean l2r = true;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        List<List<Integer>> res = new LinkedList<>();
        while (!stack.isEmpty()) {
            int size = stack.size();
            //增加一个缓冲的队列，避免影响stack的数据
            Deque<TreeNode> queue = new LinkedList<>();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = stack.pollFirst();
                list.add(node.val);
                if (l2r) {
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                } else {
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                }
            }
            //反转
            l2r = !l2r;
            res.add(list);
            //queue添加到stack中
            while (!queue.isEmpty()) {
                stack.offerFirst(queue.poll());
            }
        }
        return res;
    }
}
