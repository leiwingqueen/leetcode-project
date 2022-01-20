package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class StoneGameIXTest {
    private StoneGameIX stoneGame = new StoneGameIX();

    @Test
    public void stoneGameIX() {
        boolean res = stoneGame.stoneGameIX(new int[]{5, 1, 2, 4, 3});
        Assert.assertEquals(false, res);
    }
}