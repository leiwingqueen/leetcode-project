package com.liyongquan.linklist;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }
        ListNode cur = head, before = null, next = null;
        ListNode start = head, tail = head;
        for (int i = 0; i < n; i++) {
            if (i < m - 1) {
                before = cur;
                cur = cur.next;
                start = cur;
                tail = cur.next;
            } else {
                next = cur.next;
                cur.next = before;
                before = cur;
                cur = next;
            }
        }
        start.next = before;
        tail.next = cur;
        return head;
    }
}
