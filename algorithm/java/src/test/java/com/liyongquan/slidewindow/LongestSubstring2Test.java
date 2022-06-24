package com.liyongquan.slidewindow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LongestSubstring2Test {
    private LongestSubstring2 ls = new LongestSubstring2();

    @Test
    public void longestSubstring2() {
        int res = ls.longestSubstring2("aaabbb", 3);
        log.info("{}", res);
        Assert.assertEquals(6, res);
    }
}