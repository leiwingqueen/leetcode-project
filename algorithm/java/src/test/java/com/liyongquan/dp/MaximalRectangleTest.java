package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaximalRectangleTest {
    private MaximalRectangle mr = new MaximalRectangle();

    @Test
    public void maximalRectangle() {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        int res = mr.maximalRectangle2(matrix);
        log.info("{}", res);
        Assert.assertEquals(6, res);
    }

    @Test
    public void test() {
        char[][] matrix = {
                {'0', '1'},
        };
        int res = mr.maximalRectangle2(matrix);
        log.info("{}", res);
        Assert.assertEquals(1, res);
    }
}