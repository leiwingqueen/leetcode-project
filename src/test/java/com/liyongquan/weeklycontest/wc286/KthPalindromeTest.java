package com.liyongquan.weeklycontest.wc286;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class KthPalindromeTest {
    private KthPalindrome palindrome = new KthPalindrome();

    @Test
    public void kthPalindrome() {
        long[] res = palindrome.kthPalindrome(new int[]{1, 2, 3, 4, 5, 90}, 3);
        log.info("{}", res);
    }
}