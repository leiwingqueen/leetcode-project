package com.liyongquan.linklist;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class DeleteDuplicates2Test {
    private DeleteDuplicates2 dd = new DeleteDuplicates2();

    @Test
    public void deleteDuplicates() {
        ListNode head = build(new int[]{1, 1, 1, 2, 3});
        ListNode res = dd.deleteDuplicates(head);
        while (res != null) {
            log.info("{}", res.val);
            res = res.next;
        }
    }

    private ListNode build(int[] arr) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;
        for (int i : arr) {
            pre.next = new ListNode(i);
            pre = pre.next;
        }
        return dummy.next;
    }
}