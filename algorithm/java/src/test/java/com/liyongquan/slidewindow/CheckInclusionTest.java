package com.liyongquan.slidewindow;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CheckInclusionTest {
    private CheckInclusion ci = new CheckInclusion();

    /**
     * "ab"
     * "eidboaoo"
     */
    @Test
    public void checkInclusion() {
        boolean b = ci.checkInclusion("ab", "eidboaoo");
        System.out.println(b);
        Assert.assertEquals(false, b);
    }
}