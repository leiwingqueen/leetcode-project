package com.liyongquan.weeklycontest.wc279;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinimumTimeTest {
    private MinimumTime mt = new MinimumTime();

    @Test
    public void minimumTime() {
        int res = mt.minimumTime("1100101");
        Assert.assertEquals(5, res);
    }
}