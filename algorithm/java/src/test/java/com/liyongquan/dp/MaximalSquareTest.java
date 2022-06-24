package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class MaximalSquareTest {
    private MaximalSquare ms = new MaximalSquare();

    /**
     * [["0","1"],["1","0"]]
     */
    @Test
    public void maximalSquare() {
        char[][] matrix = {
                {'0', '1'},
                {'1', '0'}
        };
        int res = ms.maximalSquare(matrix);
        log.info("{}", res);
        Assert.assertEquals(1, res);
    }

    /**
     * [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
     */
    @Test
    public void maximalSquare2() {
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        int res = ms.maximalSquare2(matrix);
        log.info("{}", res);
        Assert.assertEquals(4,res);
    }
}