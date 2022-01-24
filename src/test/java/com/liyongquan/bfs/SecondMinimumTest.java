package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SecondMinimumTest {
    private SecondMinimum sm = new SecondMinimum();

    /**
     * 2
     * [[1,2]]
     * 3
     * 2
     */
    @Test
    public void secondMinimum3() {
        int res = sm.secondMinimum3(2, new int[][]{{1, 2}}, 3, 2);
        Assert.assertEquals(11, res);
    }
}