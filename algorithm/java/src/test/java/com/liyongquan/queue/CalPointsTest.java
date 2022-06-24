package com.liyongquan.queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CalPointsTest {
    private CalPoints cp = new CalPoints();

    @Test
    public void calPoints() {
        int res = cp.calPoints(new String[]{"5", "2", "C", "D", "+"});
        Assert.assertEquals(30, res);
    }
}