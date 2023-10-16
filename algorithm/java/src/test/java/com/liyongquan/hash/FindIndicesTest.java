package com.liyongquan.hash;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FindIndicesTest {
    private FindIndices fi = new FindIndices();

    /**
     * [5,1,4,1]
     * 2
     * 4
     */
    @Test
    public void findIndices() {
        int[] res = fi.findIndices(new int[]{5, 1, 4, 1}, 2, 4);
        Assert.assertArrayEquals(new int[]{0, 3}, res);
    }
}