package com.liyongquan.dp;


import org.junit.Assert;
import org.junit.Test;

public class TicketTest {
    @Test
    public void match() {
        //刚好满足
        Ticket ticket = new Ticket();
        int match = ticket.match(new int[]{1, 2, 5, 3}, 4);
        Assert.assertEquals(4, match);
        match = ticket.match(new int[]{1, 2, 5, 3}, 9);
        Assert.assertEquals(9, match);
        //不足的场景
        match = ticket.match(new int[]{1, 2, 5, 3}, 13);
        Assert.assertEquals(-1, match);
    }

    @Test
    public void match_over() {
        //有多余的场景
        Ticket ticket = new Ticket();
        int match = ticket.match(new int[]{1, 4, 5, 9}, 7);
        Assert.assertEquals(9, match);
    }
}