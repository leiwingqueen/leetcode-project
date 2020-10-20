package com.liyongquan.linklist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorder-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReorderList {
    /**
     * 把链表分成2部分，然后两个链表做合并。
     * 由于第二个链表需要做倒序，我们需要增加一个栈的结构来解决倒序的问题
     * <p>
     * 时间复杂度O(n)，空间复杂度O(n)
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        //链表分成两等分，使用快慢指针
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode p2 = slow.next;
        //把尾指针置空，不然会死喜欢
        slow.next = null;
        //把p2链表放入栈中
        Deque<ListNode> deque = new LinkedList<>();
        while (p2 != null) {
            deque.offerFirst(p2);
            p2 = p2.next;
        }
        //合并两个链表
        ListNode p1 = head;
        while (p1 != null && deque.size() > 0) {
            ListNode node = deque.pollFirst();
            ListNode next = p1.next;
            p1.next = node;
            node.next = next;
            p1 = next;
        }
    }

    public static void main(String[] args) {
        ListNode head = prepare(5);
        ReorderList rl = new ReorderList();
        rl.reorderList(head);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }

    private static ListNode prepare(int n) {
        ListNode head = new ListNode(1);
        ListNode pre = head;
        for (int i = 2; i <= n; i++) {
            ListNode node = new ListNode(i);
            pre.next = node;
            pre = node;
        }
        return head;
    }
}
