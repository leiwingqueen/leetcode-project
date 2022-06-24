package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NetworkBecomesIdleTest {
    private NetworkBecomesIdle networkBecomesIdle = new NetworkBecomesIdle();

    /**
     * [[0,1],[0,2],[1,2]]
     * [0,10,10]
     */
    @Test
    public void networkBecomesIdle() {
        int res = networkBecomesIdle.networkBecomesIdle(new int[][]{
                {0, 1},
                {0, 2},
                {1, 2}
        }, new int[]{0, 10, 10});
        Assert.assertEquals(3, res);
    }

    @Test
    public void networkBecomesIdle2() {
        int res = networkBecomesIdle.networkBecomesIdle(new int[][]{
                {0, 1},
                {1, 2}
        }, new int[]{0, 2, 1});
        Assert.assertEquals(8, res);
    }
}