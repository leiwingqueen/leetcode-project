package com.liyongquan.weeklycontest.wc303;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountExcellentPairsTest {
    private CountExcellentPairs pairs = new CountExcellentPairs();

    @Test
    public void countExcellentPairs() {
        long res = pairs.countExcellentPairs(new int[]{5, 5, 1}, 10);
        Assert.assertEquals(0, res);
    }
}