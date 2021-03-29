package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountDigitOneTest {
    private CountDigitOne cdo = new CountDigitOne();

    @Test
    public void countDigitOne() {
        int res = cdo.countDigitOne(13);
        log.info("{}", res);
        Assert.assertEquals(6, res);
    }
}