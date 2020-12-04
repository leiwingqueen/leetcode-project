package com.liyongquan.slidewindow;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class EqualSubstringTest {
    private EqualSubstring es = new EqualSubstring();

    /**
     * "abcd"
     * "cdef"
     * 3
     */
    @Test
    public void equalSubstring() {
        int i = es.equalSubstring("abcd", "cdef", 3);
        Assert.assertEquals(1, i);
    }
}