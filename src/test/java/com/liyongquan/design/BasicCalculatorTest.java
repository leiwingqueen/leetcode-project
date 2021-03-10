package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class BasicCalculatorTest {
    private BasicCalculator bc = new BasicCalculator();

    @Test
    public void calculate() {
        int res = bc.calculate("1 + 1");
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }

    /**
     * "- (3 + (4 + 5))"
     */
    @Test
    public void calculate2() {
        int res = bc.calculate2("- (3 + (4 + 5))");
        log.info("{}", res);
        Assert.assertEquals(-12, res);
    }
}