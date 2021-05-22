package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class XorGameTest {
    private XorGame xorGame = new XorGame();

    @Test
    public void xorGame() {
        boolean res = xorGame.xorGame(new int[]{1, 1, 2, 3});
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }

    @Test
    public void test2() {
        int[] nums = {1, 0, 0, 1, 1, 1, 1, 1, 1, 0};
        boolean res = xorGame.xorGame(nums);
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }
}