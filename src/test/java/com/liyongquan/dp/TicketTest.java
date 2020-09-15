package com.liyongquan.dp;


import org.junit.Test;

public class TicketTest {
    @Test
    public void match(){
        Ticket ticket=new Ticket();
        int match = ticket.match(new int[]{100, 200, 500, 350}, 400);
        System.out.println(match);
    }
}