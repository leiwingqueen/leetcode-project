package com.liyongquan.design;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class Xiaoxiaole4Test {

    @Test
    public void canDomain() {
        int[][] matrix = {
                {0, 0, 0, 1},
                {0, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 1, 0, 0},
        };
        Xiaoxiaole4 xiaoxiaole4 = new Xiaoxiaole4(matrix);
        boolean b = xiaoxiaole4.canDomain(new int[]{3, 0}, new int[]{0, 3});
        Assert.assertEquals(false, b);
        xiaoxiaole4.doDomain(new int[]{3, 2});
        xiaoxiaole4.doDomain(new int[]{0, 2});
        b = xiaoxiaole4.canDomain(new int[]{3, 0}, new int[]{1, 2});
        Assert.assertEquals(false, b);
        xiaoxiaole4.doDomain(new int[]{1, 2});
        b = xiaoxiaole4.canDomain(new int[]{3, 0}, new int[]{0, 1});
        Assert.assertEquals(false, b);
        xiaoxiaole4.doDomain(new int[]{2, 2});
        b = xiaoxiaole4.canDomain(new int[]{3, 0}, new int[]{0, 1});
        Assert.assertEquals(true, b);
    }
}