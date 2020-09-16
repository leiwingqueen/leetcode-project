package com.liyongquan.dp;


import org.junit.Assert;
import org.junit.Test;

public class TicketTest {
    @Test
    public void match() {
        //刚好满足
        Ticket ticket = new Ticket();
        Ticket.TicketPair match = ticket.match(new int[]{1, 2, 5, 3}, 4);
        System.out.println("发票的金额总和:" + match.weight);
        StringBuilder builder = new StringBuilder();
        for (Integer i : match.list) {
            builder.append(i + ",");
        }
        System.out.println("选择的发票序号:" + builder);
        Assert.assertEquals(4, match.weight);
        match = ticket.match(new int[]{1, 2, 5, 3}, 9);
        System.out.println("发票的金额总和:" + match.weight);
        builder = new StringBuilder();
        for (Integer i : match.list) {
            builder.append(i + ",");
        }
        System.out.println("选择的发票序号:" + builder);
        Assert.assertEquals(9, match.weight);
        //不足的场景
        match = ticket.match(new int[]{1, 2, 5, 3}, 13);
        System.out.println("发票的金额总和:" + match.weight);
        builder = new StringBuilder();
        for (Integer i : match.list) {
            builder.append(i + ",");
        }
        System.out.println("选择的发票序号:" + builder);
        Assert.assertEquals(-1, match.weight);
    }

    @Test
    public void match_over() {
        //有多余的场景
        Ticket ticket = new Ticket();
        Ticket.TicketPair match = ticket.match(new int[]{1, 4, 5, 9}, 7);
        System.out.println("发票的金额总和:" + match.weight);
        StringBuilder builder = new StringBuilder();
        for (Integer i : match.list) {
            builder.append(i + ",");
        }
        System.out.println("选择的发票序号:" + builder);
        Assert.assertEquals(9, match.weight);
    }
}