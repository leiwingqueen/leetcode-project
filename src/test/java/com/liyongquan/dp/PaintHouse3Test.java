package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class PaintHouse3Test {
    private PaintHouse3 ph = new PaintHouse3();

    /**
     * [0,0,0,0,0]
     * [[1,10],[10,1],[10,1],[1,10],[5,1]]
     * 5
     * 2
     * 3
     */
    @Test
    public void minCost() {
        int[][] cost = {{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}};
        int res = ph.minCost(new int[]{0, 0, 0, 0, 0}, cost, 5, 2, 3);
        log.info("{}", res);
    }
}