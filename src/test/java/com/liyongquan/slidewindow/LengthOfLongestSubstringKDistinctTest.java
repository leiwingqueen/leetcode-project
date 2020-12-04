package com.liyongquan.slidewindow;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LengthOfLongestSubstringKDistinctTest {
    private LengthOfLongestSubstringKDistinct ll = new LengthOfLongestSubstringKDistinct();

    @Test
    public void lengthOfLongestSubstringKDistinct() {
    }

    /**
     * "abaccc"
     * 2
     */
    @Test
    public void lengthOfLongestSubstringKDistinct2() {
        int r = ll.lengthOfLongestSubstringKDistinct2("abaccc", 2);
        System.out.println(r);
        Assert.assertEquals(4, r);
    }
}