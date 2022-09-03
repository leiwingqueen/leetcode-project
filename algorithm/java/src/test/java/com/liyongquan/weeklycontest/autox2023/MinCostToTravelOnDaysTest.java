package com.liyongquan.weeklycontest.autox2023;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinCostToTravelOnDaysTest {
    private MinCostToTravelOnDays minCostToTravelOnDays = new MinCostToTravelOnDays();

    @Test
    public void minCostToTravelOnDays() {
        long res = minCostToTravelOnDays.minCostToTravelOnDays(new int[]{1, 2, 3, 4}, new int[][]{{1, 3}, {2, 5}, {3, 7}});
        Assert.assertEquals(10, res);
    }
}