package com.liyongquan.slidewindow;

import org.junit.Test;
import sun.rmi.runtime.Log;

import static org.junit.Assert.*;

public class LongestSubstringTest {
    private LongestSubstring longestSubstring=new LongestSubstring();
    @Test
    public void lengthOfLongestSubstring2() {
        int count = longestSubstring.lengthOfLongestSubstring2(" ");
        System.out.println(count);
    }
}