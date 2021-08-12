package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/12
 */
@Slf4j
public class ShortestPathLengthTest {
    private ShortestPathLength spl = new ShortestPathLength();

    @Test
    public void shortestPathLength() {
        int[][] graph = {
                {1, 2, 3},
                {0},
                {0},
                {0}
        };
        int res = spl.shortestPathLength(graph);
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}