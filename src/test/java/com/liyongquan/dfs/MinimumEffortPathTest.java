package com.liyongquan.dfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinimumEffortPathTest {
    private MinimumEffortPath me = new MinimumEffortPath();

    @Test
    public void minimumEffortPath() {
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        int res = me.minimumEffortPath(heights);
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }

    @Test
    public void minimumEffortPath2(){
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        int res = me.minimumEffortPath2(heights);
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }
}