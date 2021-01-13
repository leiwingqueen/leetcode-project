package com.liyongquan.dp;


import org.junit.Assert;
import org.junit.Test;

public class TicketTest {
    @Test
    public void match() {
        //刚好满足
        baseTest(new int[]{1, 2, 5, 3}, 4, 4);
        baseTest(new int[]{1, 2, 5, 3}, 9, 9);
        //不足的场景
        baseTest(new int[]{1, 2, 5, 3}, 13, -1);
    }

    @Test
    public void matchZero() {
        baseTest(new int[]{0, 1}, 1, 1);
    }

    @Test
    public void match_over() {
        baseTest(new int[]{1, 4, 5, 9}, 7, 9);
        baseTest(new int[]{1, 4, 5, 9, 34, 5, 4}, 16, 17);
    }

    private void baseTest(int[] tickets, int w, int expect) {
        System.out.println("=================================");
        Ticket ticket = new Ticket();
        StringBuilder builder = new StringBuilder();
        for (int i : tickets) {
            builder.append(i + ",");
        }
        System.out.println("Q:发票列表:" + builder + "台账金额:" + w);
        Ticket.TicketPair match = ticket.match(tickets, w);
        System.out.println("A:发票的金额总和:" + match.weight);
        builder = new StringBuilder();
        for (Integer i : match.list) {
            builder.append((i + 1) + ",");
        }
        System.out.println("选择的发票序号:" + builder);
        Assert.assertEquals(expect, match.weight);
    }
}