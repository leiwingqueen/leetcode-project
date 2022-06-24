package com.liyongquan.weeklycontest.wc281;

import com.liyongquan.linklist.ListNode;

public class MergeNodes {
    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode p1 = head.next, p2 = dummy;
        int sum = 0;
        while (p1 != null) {
            if (p1.val == 0) {
                p2.next = new ListNode(sum);
                p2 = p2.next;
                sum = 0;
            } else {
                sum += p1.val;
            }
            p1 = p1.next;
        }
        return dummy.next;
    }
}
