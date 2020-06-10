package com.liyongquan.linklist;

import java.util.List;
import java.util.Stack;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseLinkedList {
    /**
     * 反转(递归)
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode[] reverse = reverse(head);
        return reverse[0];
    }

    private ListNode[] reverse(ListNode head) {
        if (head == null || head.next == null) {
            return new ListNode[]{head, head};
        }
        ListNode[] reverse = reverse(head.next);
        head.next = null;
        reverse[1].next = head;
        return new ListNode[]{reverse[0], head};
    }

    /**
     * 迭代解法
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode node = head;
        while (node != null) {
            stack.push(node);
        }
        ListNode newhead = stack.pop();
        ListNode cur = newhead;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            pop.next = null;
            cur.next = pop;
            cur = cur.next;
        }
        return newhead;
    }
}
