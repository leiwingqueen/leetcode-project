package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class PaintN3GridTest {
    private PaintN3Grid grid = new PaintN3Grid();

    @Test
    public void numOfWays() {
        int res = grid.numOfWays(2);
        log.info("{}", res);
        Assert.assertEquals(54, res);
    }

    @Test
    public void check() {
        boolean check = grid.check(12, 210);
        Assert.assertEquals(false, check);
    }
}