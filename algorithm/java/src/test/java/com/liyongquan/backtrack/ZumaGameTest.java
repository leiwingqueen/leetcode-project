package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author liyongquan
 * @date 2021/11/12
 */
@Slf4j
public class ZumaGameTest {
    @Test
    public void findMinStep() {
        ZumaGame zumaGame = new ZumaGame();
        int r = zumaGame.findMinStep("RRWWRRBBRR", "WB");
        log.info("{}", r);
        Assert.assertEquals(2, r);
    }
}