package com.liyongquan.linklist;

import org.junit.Test;

import static org.junit.Assert.*;

public class OddEvenListTest {
    private OddEvenList oddEvenList = new OddEvenList();

    @Test
    public void oddEvenList() {
        ListNode head = new ListNode(1);
        ListNode pre = head;
        for (int i = 2; i <= 4; i++) {
            pre.next = new ListNode(i);
            pre = pre.next;
        }
        ListNode listNode = oddEvenList.oddEvenList(head);
        while (listNode != null) {
            System.out.print(listNode.val + "->");
            listNode = listNode.next;
        }
    }
}