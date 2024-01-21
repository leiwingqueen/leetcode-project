package com.liyongquan.tyy;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class QqTest {
    @Test
    public void cal() {
        int[] cal = Qq.cal(1970, 1, 1);
        Assert.assertArrayEquals(new int[]{4, 1}, cal);
        cal = Qq.cal(1970, 1, 2);
        Assert.assertArrayEquals(new int[]{5, 2}, cal);
        cal = Qq.cal(2024, 1, 20);
        Assert.assertArrayEquals(new int[]{6, 20}, cal);
        cal = Qq.cal(2024, 2, 20);
        Assert.assertArrayEquals(new int[]{2, 51}, cal);
        cal = Qq.cal(2016, 3, 1);
        Assert.assertArrayEquals(new int[]{2, 61}, cal);
    }
}