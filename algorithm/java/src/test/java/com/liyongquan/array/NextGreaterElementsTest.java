package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NextGreaterElementsTest {
    private NextGreaterElements nge = new NextGreaterElements();

    @Test
    public void nextGreaterElements() {
        int[] res = nge.nextGreaterElements2(new int[]{1, 2, 1});
        Assert.assertArrayEquals(new int[]{2, -1, 2}, res);
    }
}