package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CanCrossTest {
    private CanCross canCross = new CanCross();

    @Test
    public void canCross() {
        boolean res = canCross.canCross(new int[]{0, 1, 2, 3, 4, 8, 9, 11});
        log.info("{}", res);
        Assert.assertEquals(false, res);
    }
}