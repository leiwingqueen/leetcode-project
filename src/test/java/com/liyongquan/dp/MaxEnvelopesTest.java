package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import javax.lang.model.element.VariableElement;

import static org.junit.Assert.*;

@Slf4j
public class MaxEnvelopesTest {
    private MaxEnvelopes me = new MaxEnvelopes();

    @Test
    public void maxEnvelopes() {
        int[][] matrix = {
                {2, 100},
                {3, 200},
                {4, 300},
                {5, 500},
                {5, 400},
                {5, 250},
                {6, 370},
                {6, 360},
                {7, 380}
        };
        int res = me.maxEnvelopes(matrix);
        log.info("{}", res);
        Assert.assertEquals(5, res);
    }

    @Test
    public void maxEnvelopes3() {
        int[][] matrix = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        int res = me.maxEnvelopes3(matrix);
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }

    @Test
    public void test() {
        int[][] matrix = {
                {2, 100},
                {3, 200},
                {4, 300},
                {5, 250},
                {5, 400},
                {5, 500},
                {6, 360},
                {6, 370},
                {7, 380}
        };
        int res = me.maxEnvelopes3(matrix);
        log.info("{}", res);
        Assert.assertEquals(5, res);
    }

    @Test
    public void test2() {
        int[][] matrix = {{1, 1}, {1, 1}, {1, 1}};
        int res = me.maxEnvelopes3(matrix);
        log.info("{}", res);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test3() {
        int[][] matrix = {{1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4}, {9, 5}};
        int res = me.maxEnvelopes3(matrix);
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }

    @Test
    public void test4() {
        int[][] matrix = {{46, 89}, {50, 53}, {52, 68}, {72, 45}, {77, 81}};
        int res = me.maxEnvelopes3(matrix);
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}