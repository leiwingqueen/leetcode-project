package com.liyongquan.tree;

import com.liyongquan.linklist.ListNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[1,2,3,4,5,null,7,8]
 * <p>
 * 1
 * /  \
 * 2    3
 * / \    \
 * 4   5    7
 * /
 * 8
 * <p>
 * 输出：[[1],[2,3],[4,5,7],[8]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/list-of-depth-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ListOfDepth {
    /**
     * 最简单的解法，bfs。时间复杂度O(n)，空间复杂度O(1)【不算上返回的结果】
     *
     * @param tree
     * @return
     */
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return new ListNode[]{};
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(tree);
        List<ListNode> list = new LinkedList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode sentinel = new ListNode(0);
            ListNode pre = sentinel;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                pre.next = new ListNode(poll.val);
                pre = pre.next;
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            list.add(sentinel.next);
        }
        //转换成array
        ListNode[] result = new ListNode[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}
