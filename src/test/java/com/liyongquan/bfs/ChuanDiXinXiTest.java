package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ChuanDiXinXiTest {
    private ChuanDiXinXi cdx = new ChuanDiXinXi();

    /**
     * 5
     * [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]]
     * 3
     */
    @Test
    public void numWays() {
        int[][] relation = {
                {0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}
        };
        int res = cdx.numWays(5, relation, 3);
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}