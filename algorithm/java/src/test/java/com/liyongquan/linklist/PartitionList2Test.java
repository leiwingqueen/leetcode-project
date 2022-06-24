package com.liyongquan.linklist;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class PartitionList2Test {

    @Test
    public void partition() {
        PartitionList2 pl = new PartitionList2();
        ListNode partition = pl.partition(new ListNode(1), 0);
        while (partition != null) {
            log.info("{}", partition.val);
            partition = partition.next;
        }
    }

    @Test
    public void partition2() {
        PartitionList2 pl = new PartitionList2();
        ListNode partition = pl.partition(ListNode.build(new int[]{3, 1, 2}), 3);
        while (partition != null) {
            log.info("{}", partition.val);
            partition = partition.next;
        }
    }

    @Test
    public void partition3() {
        PartitionList2 pl = new PartitionList2();
        ListNode partition = pl.partition(ListNode.build(new int[]{2, 1}), 2);
        while (partition != null) {
            log.info("{}", partition.val);
            partition = partition.next;
        }
    }
}