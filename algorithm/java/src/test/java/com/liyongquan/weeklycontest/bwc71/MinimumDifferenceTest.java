package com.liyongquan.weeklycontest.bwc71;

import com.liyongquan.weeklycontest.wc279.MinimumTime;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinimumDifferenceTest {
    private MinimumDifference md = new MinimumDifference();

    @Test
    public void minimumDifference() {
        long res = md.minimumDifference(new int[]{3, 1, 2});
        Assert.assertEquals(-1, res);
    }
}