package com.liyongquan.linklist;

import com.liyongquan.dp.NumDecodings;

import java.util.List;

/**
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * 示例 2:
 * <p>
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 * <p>
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/odd-even-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class OddEvenList {
    /**
     * 非原地算法。
     * 使用两个指针(奇偶)遍历链表，然后将两个链表合并
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode[] rec = rec(head);
        rec[1].next = rec[2];
        return rec[0];
    }

    /**
     * @param head
     * @return [奇数链表头指针, 奇数链表尾指针, 偶数链表头指针]
     */
    private ListNode[] rec(ListNode head) {
        if (head == null || head.next == null) {
            return new ListNode[]{head, head, null};
        }
        ListNode[] rec = rec(head.next.next);
        ListNode p1 = head;
        ListNode p3 = p1.next;
        p3.next = rec[2];
        ListNode p2 = rec[1] == null ? head : rec[1];
        p2.next = null;
        p1.next = rec[0];
        return new ListNode[]{p1, p2, p3};
    }
}
