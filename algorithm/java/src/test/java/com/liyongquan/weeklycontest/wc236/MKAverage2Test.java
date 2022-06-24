package com.liyongquan.weeklycontest.wc236;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MKAverage2Test {

    @Test
    public void calculateMKAverage() {
        MKAverage2 mkAverage = new MKAverage2(3, 1);
        mkAverage.addElement(3);
        mkAverage.addElement(1);
        mkAverage.addElement(10);
        int res = mkAverage.calculateMKAverage();
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}