package com.liyongquan.weeklycontest.wc281;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RepeatLimitedStringTest {
    private RepeatLimitedString rls = new RepeatLimitedString();

    /**
     * "cczazcc"
     * 3
     */
    @Test
    public void repeatLimitedString() {
        String res = rls.repeatLimitedString("cczazcc", 3);
        Assert.assertEquals("zzcccac", res);
    }

    /**
     * "aababab"
     * 2
     */
    @Test
    public void repeatLimitedString2() {
        String res = rls.repeatLimitedString("aababab", 2);
        Assert.assertEquals("bbabaa", res);
    }
}