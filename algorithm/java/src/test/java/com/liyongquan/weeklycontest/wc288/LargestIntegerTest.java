package com.liyongquan.weeklycontest.wc288;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LargestIntegerTest {
    private LargestInteger li = new LargestInteger();

    @Test
    public void largestInteger() {
        int res = li.largestInteger(1234);
        Assert.assertEquals(3412, res);
    }
}