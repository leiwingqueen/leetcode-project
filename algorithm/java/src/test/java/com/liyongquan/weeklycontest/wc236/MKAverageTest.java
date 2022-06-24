package com.liyongquan.weeklycontest.wc236;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MKAverageTest {
    @Test
    public void calculateMKAverage() {
        MKAverage mkAverage = new MKAverage(3, 1);
        mkAverage.addElement(3);
        mkAverage.addElement(1);
        mkAverage.addElement(10);
        int res = mkAverage.calculateMKAverage();
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }

    @Test
    public void test() {
        MKAverage mkAverage = new MKAverage(30, 10);

    }
}