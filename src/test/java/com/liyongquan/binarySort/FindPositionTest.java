package com.liyongquan.binarySort;

import org.junit.Test;

import static org.junit.Assert.*;

public class FindPositionTest {
    private FindPosition position = new FindPosition();

    @Test
    public void searchRange() {
        int[] ints = position.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        assertEquals(3,ints[0]);
        assertEquals(4,ints[1]);
    }
}