package com.liyongquan.heap;

import com.liyongquan.weeklycontest.autox2023.MinCostToTravelOnDays;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MincostToHireWorkersTest {
    private MincostToHireWorkers days = new MincostToHireWorkers();

    /**
     * [10,20,5]
     * [70,50,30]
     * 2
     */
    @Test
    public void mincostToHireWorkers() {
        double res = days.mincostToHireWorkers(new int[]{10, 20, 5}, new int[]{70, 50, 30}, 2);
        Assert.assertEquals(105D, res);
    }
}