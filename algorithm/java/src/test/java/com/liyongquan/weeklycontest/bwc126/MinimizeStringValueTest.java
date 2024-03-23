package com.liyongquan.weeklycontest.bwc126;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinimizeStringValueTest {
    private MinimizeStringValue sv = new MinimizeStringValue();

    @Test
    public void minimizeStringValue() {
        String s = sv.minimizeStringValue("???");
        Assert.assertEquals("abc", s);
    }
}