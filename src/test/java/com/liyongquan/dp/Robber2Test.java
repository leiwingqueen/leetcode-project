package com.liyongquan.dp;

import org.junit.Test;

import static org.junit.Assert.*;

public class Robber2Test {
    private Robber2 robber2=new Robber2();
    @Test
    public void rob() {
        int rob = robber2.rob(new int[]{2, 3, 2});
        System.out.println(rob);
    }
}