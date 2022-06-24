package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CanIWinTest {

    @Test
    public void canIWin2() {
        CanIWin canIWin = new CanIWin();
        boolean res = canIWin.canIWin2(5, 50);
        Assert.assertEquals(false, res);
    }
}