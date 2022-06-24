package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RomanToIntTest {
    private RomanToInt romanToInt = new RomanToInt();

    @Test
    public void romanToInt() {
        int res = romanToInt.romanToInt("III");
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}