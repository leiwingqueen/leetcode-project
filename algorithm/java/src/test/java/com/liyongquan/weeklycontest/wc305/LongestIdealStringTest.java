package com.liyongquan.weeklycontest.wc305;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LongestIdealStringTest {
    private LongestIdealString longestIdealString = new LongestIdealString();

    // "acfgbd"
    //2
    @Test
    public void longestIdealString() {
        int res = longestIdealString.longestIdealString("acfgbd", 2);
        Assert.assertEquals(4, res);
    }
}