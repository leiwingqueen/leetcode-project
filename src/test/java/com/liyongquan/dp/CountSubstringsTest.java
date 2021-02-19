package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountSubstringsTest {
    private CountSubstrings cs = new CountSubstrings();

    @Test
    public void countSubstrings() {
        int res = cs.countSubstrings("aaa");
        log.info("{}", res);
        Assert.assertEquals(6, res);
    }
}