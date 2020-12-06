package com.liyongquan.slidewindow;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShortestSeqTest {
    private ShortestSeq ss = new ShortestSeq();

    @Test
    public void shortestSeq() {
        int[] big = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
        int[] small = {1, 5, 9};
        int[] seq = ss.shortestSeq(big, small);
        Assert.assertArrayEquals(new int[]{7, 10}, seq);
    }
}