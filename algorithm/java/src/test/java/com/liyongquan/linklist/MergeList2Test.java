package com.liyongquan.linklist;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MergeList2Test {
    private MergeList2 ml = new MergeList2();

    /**
     * [[1,4,5],[1,3,4],[2,6]]
     */
    @Test
    public void mergeKLists() {
        int[][] array = {
                {1, 4, 5},
                {1, 3, 4},
                {2, 6}
        };
        ListNode node = ml.mergeKLists2(build(array));
    }

    private ListNode[] build(int[][] array) {
        ListNode[] res = new ListNode[array.length];
        for (int i = 0; i < array.length; i++) {
            ListNode head = new ListNode(0);
            ListNode cur = head;
            for (Integer num : array[i]) {
                cur.next = new ListNode(num);
                cur = cur.next;
            }
            res[i] = head.next;
        }
        return res;
    }
}