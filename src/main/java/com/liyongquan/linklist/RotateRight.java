package com.liyongquan.linklist;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * <p>
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/rotate-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RotateRight {
    /**
     * 首先我们要明白
     * 1.链表长度为n,移动k*n次相当于不需要移动，则有移动k等价于k%n
     * 2.向右移动k，可以把链表分成2部分 0~n-k-1,n-k~n-1。转换后的链表为n-k~n-1->0~n-k-1
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int size = 0;
        ListNode cur = head, tail = head;
        while (cur != null) {
            tail = cur;
            cur = cur.next;
            size++;
        }
        k = k % size;
        if (k == 0) {
            return head;
        }
        cur = head;
        int c = 0;
        while (c < size - k - 1) {
            cur = cur.next;
            c++;
        }
        tail.next = head;
        head = cur.next;
        cur.next = null;
        return head;
    }

    /**
     * 先构成环，然后移动若干位置再掐断
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        int size = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            size++;
        }
        //收尾相连
        tail.next = head;
        k %= size;
        ListNode cur = head;
        for (int i = 0; i < size - k - 1; i++) {
            cur = cur.next;
        }
        head = cur.next;
        cur.next = null;
        return head;
    }
}
