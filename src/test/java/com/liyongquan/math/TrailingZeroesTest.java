package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class TrailingZeroesTest {
    private TrailingZeroes zeroes = new TrailingZeroes();

    @Test
    public void trailingZeroes2() {
        int res = zeroes.trailingZeroes2(15);
        Assert.assertEquals(3, res);
    }
}