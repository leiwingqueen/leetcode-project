package com.liyongquan.linklist;

import java.util.Stack;

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
    /**
     * 栈的解法
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }
        //哑节点
        ListNode tmp = new ListNode(0);
        tmp.next = head;
        //找到反转前的第一个节点
        ListNode start = tmp;
        for (int i = 0; i < m - 1; i++) {
            start = start.next;
        }
        //入栈
        Stack<ListNode> stack = new Stack<>();
        ListNode node = start.next;
        for (int i = m; i <= n; i++) {
            stack.push(node);
            node = node.next;
        }
        ListNode tail = node;
        //翻转处理
        ListNode pre = start;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            pre.next = pop;
            pre = pop;
        }
        pre.next = tail;
        return tmp.next;
    }
}
