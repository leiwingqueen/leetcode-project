package com.liyongquan.bit;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClosedNumberTest {
    private ClosedNumber cn = new ClosedNumber();

    @Test
    public void findClosedNumbers2() {
        int[] result = cn.findClosedNumbers2(571603719);
        Assert.assertArrayEquals(new int[]{571603723, 571603696}, result);
    }
}