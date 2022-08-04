package com.liyongquan.weeklycontest.bwc82;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LatestTimeCatchTheBusTest {
    private LatestTimeCatchTheBus catchTheBus = new LatestTimeCatchTheBus();

    // [10,20]
    //[2,17,18,19]
    //2
    @Test
    public void latestTimeCatchTheBus() {
        int res = catchTheBus.latestTimeCatchTheBus(new int[]{10, 20}, new int[]{2, 17, 18, 19}, 2);
        Assert.assertEquals(16, res);
    }

    @Test
    public void test2() {
        int res = catchTheBus.latestTimeCatchTheBus(new int[]{3}, new int[]{2, 4}, 2);
        Assert.assertEquals(3, res);
    }
}