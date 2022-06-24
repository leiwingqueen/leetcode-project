package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SubstringTest {
    private Substring st = new Substring();

    @Test
    public void longestPalindrome() {
        String res = st.longestPalindrome2("babad");
        log.info("res:{}", res);
    }
}