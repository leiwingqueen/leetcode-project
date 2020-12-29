package com.liyongquan.slidewindow;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinWindowTest {
    private MinWindow mw = new MinWindow();


    @Test
    public void minWindow() {
        String s = mw.minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
        Assert.assertEquals("BANC", s);
    }

    @Test
    public void minWindow2() {
        String s = mw.minWindow("a", "aa");
        System.out.println(s);
        Assert.assertEquals("", s);
    }

}