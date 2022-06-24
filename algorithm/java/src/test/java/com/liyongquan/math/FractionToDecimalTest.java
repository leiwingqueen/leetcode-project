package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/10/3
 */
@Slf4j
public class FractionToDecimalTest {
    private FractionToDecimal ftd = new FractionToDecimal();

    @Test
    public void fractionToDecimal() {
        String res = ftd.fractionToDecimal(1, 2);
        Assert.assertEquals("0.5", res);
    }

    @Test
    public void testFractionToDecimal() {
        String s = ftd.fractionToDecimal(2, 3);
        Assert.assertEquals("0.(6)", s);
    }
}