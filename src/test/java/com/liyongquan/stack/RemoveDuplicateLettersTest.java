package com.liyongquan.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RemoveDuplicateLettersTest {
    private RemoveDuplicateLetters rd = new RemoveDuplicateLetters();

    @Test
    public void removeDuplicateLetters() {
        String res = rd.removeDuplicateLetters("bcabc");
        log.info("{}", res);
        Assert.assertEquals("abc", res);
    }

    @Test
    public void test2() {
        String res = rd.removeDuplicateLetters("bbcaac");
        log.info("{}", res);
        Assert.assertEquals("bac", res);
    }
}