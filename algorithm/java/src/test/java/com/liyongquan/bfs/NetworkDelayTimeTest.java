package com.liyongquan.bfs;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/2
 */
@Slf4j
public class NetworkDelayTimeTest {
    private NetworkDelayTime ndt = new NetworkDelayTime();

    //times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
    @Test
    public void networkDelayTime() {
        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };
        int res = ndt.networkDelayTime(times, 4, 2);
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }
}