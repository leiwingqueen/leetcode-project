package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

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
}