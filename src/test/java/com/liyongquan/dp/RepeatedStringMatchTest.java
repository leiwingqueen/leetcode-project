package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RepeatedStringMatchTest {
    private RepeatedStringMatch rsm = new RepeatedStringMatch();

    @Test
    public void repeatedStringMatch() {
        int res = rsm.repeatedStringMatch("abcd",
                "cdabcdab");
        Assert.assertEquals(3, res);
    }

    @Test
    public void test2() {
        int res = rsm.repeatedStringMatch("a",
                "aa");
        Assert.assertEquals(2, res);
    }
}