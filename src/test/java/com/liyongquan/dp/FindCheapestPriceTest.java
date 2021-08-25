package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/25
 */
@Slf4j
public class FindCheapestPriceTest {
    private FindCheapestPrice fcp = new FindCheapestPrice();

    /**
     * 4
     * [[0,1,1],[0,2,5],[1,2,1],[2,3,1]]
     * 0
     * 3
     * 1
     */
    @Test
    public void findCheapestPrice2() {
        int[][] flights = {
                {0, 1, 1},
                {0, 2, 5},
                {1, 2, 1},
                {2, 3, 1},
        };
        int res = fcp.findCheapestPrice2(4, flights, 0, 3, 1);
        log.info("{}", res);
        Assert.assertEquals(6, res);
    }

    /**
     * 3
     * [[0,1,2],[1,2,1],[2,0,10]]
     * 1
     * 2
     * 1
     */
    @Test
    public void test2() {
        int[][] flights = {
                {0, 1, 2},
                {1, 2, 1},
                {2, 0, 10},
        };
        int res = fcp.findCheapestPrice2(3, flights, 1, 2, 1);
        log.info("{}", res);
        Assert.assertEquals(1, res);
    }
}