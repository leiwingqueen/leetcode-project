package com.liyongquan.slidewindow;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongestOnesTest {
    private LongestOnes lo = new LongestOnes();

    @Test
    public void longestOnes() {
        int i = lo.longestOnes(new int[]{0, 0, 0, 1}, 4);
        System.out.println(i);
        Assert.assertEquals(4, i);
    }
}