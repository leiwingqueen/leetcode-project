package com.liyongquan.weeklycontest.wc291;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinimumCardPickupTest {
    private MinimumCardPickup pickup = new MinimumCardPickup();

    /**
     * [3,4,2,3,4,7]
     */
    @Test
    public void minimumCardPickup() {
        int res = pickup.minimumCardPickup(new int[]{3, 4, 2, 3, 4, 7});
        Assert.assertEquals(4, res);
    }

    /**
     * [70,37,70,41,1,62,71,49,38,50,29,76,29,41,22,66,88,18,85,53]
     */
    @Test
    public void test1() {
        int res = pickup.minimumCardPickup(new int[]{70, 37, 70, 41, 1, 62, 71, 49, 38, 50, 29, 76, 29, 41, 22, 66, 88, 18, 85, 53});
        Assert.assertEquals(3, res);
    }
}