package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountMaxOrSubsetsTest {
    private CountMaxOrSubsets subsets = new CountMaxOrSubsets();

    @Test
    public void countMaxOrSubsets() {
        int r = subsets.countMaxOrSubsets(new int[]{3, 1});
        Assert.assertEquals(2, r);
    }
}