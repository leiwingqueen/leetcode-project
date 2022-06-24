package com.liyongquan.slidewindow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ContainsDuplicate3Test {
    private ContainsDuplicate3 cd = new ContainsDuplicate3();

    /**
     * [1,2,3,1]
     * 3
     * 0
     */
    @Test
    public void containsNearbyAlmostDuplicate() {
        boolean res = cd.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0);
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }

    /**
     * [1,0,1,1]
     * 1
     * 2
     */
    @Test
    public void test2() {
        boolean res = cd.containsNearbyAlmostDuplicate(new int[]{1, 0, 1, 1}, 1, 2);
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }
}