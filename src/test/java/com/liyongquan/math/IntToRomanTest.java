package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class IntToRomanTest {
    private IntToRoman intToRoman = new IntToRoman();

    @Test
    public void intToRoman() {
        String res = intToRoman.intToRoman(3);
        log.info("{}", res);
        Assert.assertEquals("III", res);
    }

    @Test
    public void intToRoman2() {
        String res = intToRoman.intToRoman(58);
        log.info("{}", res);
        Assert.assertEquals("LVIII", res);
    }
}