package com.liyongquan.twopointer;

import org.junit.Test;

import static org.junit.Assert.*;

public class SmallestDifferenceTest {
    private SmallestDifference sd = new SmallestDifference();

    /**
     * [-2147483648,1]
     * [2147483647,0]
     */
    @Test
    public void smallestDifference() {
        int res = sd.smallestDifference(new int[]{-2147483648, 1}, new int[]{2147483647, 0});
        System.out.println(res);
    }
}