package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class Find132patternTest {
    private Find132pattern pattern = new Find132pattern();

    @Test
    public void find132pattern2() {
        boolean res = pattern.find132pattern2(new int[]{3, 1, 4, 2});
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }
}