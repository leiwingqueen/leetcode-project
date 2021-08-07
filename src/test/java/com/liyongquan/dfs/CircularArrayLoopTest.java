package com.liyongquan.dfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/7
 */
@Slf4j
public class CircularArrayLoopTest {
    private CircularArrayLoop loop = new CircularArrayLoop();

    @Test
    public void circularArrayLoop() {
        boolean res = loop.circularArrayLoop(new int[]{2, -1, 1, 2, 2});
        Assert.assertEquals(true, res);
    }
}