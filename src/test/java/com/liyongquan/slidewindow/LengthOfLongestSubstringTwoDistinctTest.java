package com.liyongquan.slidewindow;

import org.junit.Test;

import static org.junit.Assert.*;

public class LengthOfLongestSubstringTwoDistinctTest {
    private LengthOfLongestSubstringTwoDistinct td = new LengthOfLongestSubstringTwoDistinct();

    @Test
    public void lengthOfLongestSubstringTwoDistinct() {
        int r = td.lengthOfLongestSubstringTwoDistinct("abcabcabc");
        System.out.println(r);
    }

    @Test
    public void test() {
        int r = td.lengthOfLongestSubstringTwoDistinct("eceba");
        System.out.println(r);
    }
}