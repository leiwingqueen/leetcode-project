package com.liyongquan.linklist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddTwoNumber2 {
    /**
     * 先进行链表翻转，然后再进行加法
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //反转
        ListNode p1 = reverse(l1)[0];
        ListNode p2 = reverse(l2)[0];
        //从低位开始进行加法
        ListNode head = new ListNode(0);
        ListNode p3 = head;
        while (p1 != null || p2 != null) {
            int sum = (p1 == null ? 0 : p1.val) + (p2 == null ? 0 : p2.val) + p3.val;
            int up = 0;
            if (sum >= 10) {
                sum -= 10;
                up = 1;
            }
            p3.val = sum;
            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
            if (p1 != null || p2 != null || up > 0) {
                p3.next = new ListNode(up);
                p3 = p3.next;
            }
        }
        //最终结果反转
        return reverse(head)[0];
    }

    private ListNode[] reverse(ListNode l) {
        if (l == null) {
            return new ListNode[]{null, null};
        }
        if (l.next == null) {
            return new ListNode[]{l, l};
        }
        ListNode[] reverse = reverse(l.next);
        reverse[1].next = l;
        l.next = null;
        return new ListNode[]{reverse[0], l};
    }

    /**
     * 栈解法
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Deque<Integer> s1 = new LinkedList<>(), s2 = new LinkedList<>(), s3 = new LinkedList<>();
        while (l1 != null) {
            s1.offerFirst(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.offerFirst(l2.val);
            l2 = l2.next;
        }
        int up = 0;
        while (s1.size() > 0 || s2.size() > 0) {
            int sum = up + (s1.size() > 0 ? s1.pollFirst() : 0) + (s2.size() > 0 ? s2.pollFirst() : 0);
            if (sum >= 10) {
                up = 1;
                sum -= 10;
            } else {
                up = 0;
            }
            s3.offerFirst(sum);
        }
        ListNode tmp = new ListNode(0);
        ListNode pre = tmp;
        while (s3.size() > 0) {
            ListNode node = new ListNode(s3.pollFirst());
            pre.next = node;
            pre = pre.next;
        }
        return tmp.next;
    }
}
