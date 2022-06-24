package com.liyongquan.dp;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlipTest {
    private Flip flip=new Flip();

    @Test
    public void minFlipsMonoIncr() {
        int i = flip.minFlipsMonoIncr("00110");
        System.out.println(i);
    }
}