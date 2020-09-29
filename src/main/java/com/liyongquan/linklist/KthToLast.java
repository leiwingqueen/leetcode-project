package com.liyongquan.linklist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 * <p>
 * 注意：本题相对原题稍作改动
 * <p>
 * 示例：
 * <p>
 * 输入： 1->2->3->4->5 和 k = 2
 * 输出： 4
 * 说明：
 * <p>
 * 给定的 k 保证是有效的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthToLast {
    /**
     * 栈的方式解决
     * <p>
     * 时间复杂度O(n+k),空间复杂度O(n)
     * n为链表的结点数
     *
     * @param head
     * @param k
     * @return
     */
    public int kthToLast(ListNode head, int k) {
        Deque<Integer> stack = new LinkedList<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        for (int i = 0; i < k - 1; i++) {
            stack.poll();
        }
        return stack.peek();
    }

    /**
     * 快慢指针解法。时间复杂度O(n)，空间复杂度O(1)
     *
     * @param head
     * @param k
     * @return
     */
    public int kthToLast2(ListNode head, int k) {
        ListNode p1 = head, p2 = head;
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2.val;
    }
}
