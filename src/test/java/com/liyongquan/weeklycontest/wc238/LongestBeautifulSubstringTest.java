package com.liyongquan.weeklycontest.wc238;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LongestBeautifulSubstringTest {
    private LongestBeautifulSubstring lbs = new LongestBeautifulSubstring();

    @Test
    public void longestBeautifulSubstring() {
        int res = lbs.longestBeautifulSubstring("aeiaaioaaaaeiiiiouuuooaauuaeiu");
        log.info("{}", res);
    }
}