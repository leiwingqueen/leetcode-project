package com.liyongquan.weeklycontest.bwc70;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NumberOfWaysTest {
    private NumberOfWays ways = new NumberOfWays();

    @Test
    public void numberOfWays() {
        int res = ways.numberOfWays("SSPPSPS");
        Assert.assertEquals(3, res);
    }
}