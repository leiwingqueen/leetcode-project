package com.liyongquan.weeklycontest.wc301;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class fillCupsTest {
    private FillCups fillCups = new FillCups();

    @Test
    public void fillCups2() {
        int res = fillCups.fillCups2(new int[]{1, 4, 2});
        Assert.assertEquals(4, res);
    }
}