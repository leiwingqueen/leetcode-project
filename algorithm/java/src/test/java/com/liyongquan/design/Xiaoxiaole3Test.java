package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class Xiaoxiaole3Test {
    private Xiaoxiaole3 xiaoxiaole3 = new Xiaoxiaole3();

    @Test
    public void canDomain() {
        int[][] matrix = {
                {0, 0, 0, 1},
                {0, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 1, 0, 0},
        };
        boolean b = xiaoxiaole3.canDomain(matrix, new int[]{3, 0}, new int[]{0, 3});
        Assert.assertEquals(false, b);
    }

    @Test
    public void canDomain1() {
        int[][] matrix = {
                {0, 0, 1, 1},
                {0, 0, 1, 0},
                {1, 0, 1, 0},
                {1, 1, 1, 0},
        };
        boolean b = xiaoxiaole3.canDomain(matrix, new int[]{3, 0}, new int[]{0, 3});
        Assert.assertEquals(true, b);
    }
}