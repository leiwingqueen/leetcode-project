package com.liyongquan.array;

import org.junit.Test;

public class LongestSubstringTest {
    private LongestSubstring substring=new LongestSubstring();
    @Test
    public void lengthOfLongestSubstring(){
        int result = substring.lengthOfLongestSubstring("abcabcbb");
        System.out.println(result);
    }
}