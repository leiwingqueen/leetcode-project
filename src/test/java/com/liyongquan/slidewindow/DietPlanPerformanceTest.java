package com.liyongquan.slidewindow;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DietPlanPerformanceTest {
    private DietPlanPerformance dp = new DietPlanPerformance();

    /**
     * [1,2,3,4,5]
     * 1
     * 3
     * 3
     */
    @Test
    public void dietPlanPerformance() {
        int i = dp.dietPlanPerformance(new int[]{1, 2, 3, 4, 5}, 1, 3, 3);
        System.out.println(i);
        Assert.assertEquals(0, i);
    }
}