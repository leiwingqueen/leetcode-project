package com.liyongquan.weeklycontest.bwc80;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountSubarraysTest {
    private CountSubarrays subarrays = new CountSubarrays();

    @Test
    public void countSubarrays() {
        long res = subarrays.countSubarrays(new int[]{5, 9, 7, 5, 8, 4, 4, 3, 9, 4, 6, 8, 10, 8, 1, 1, 9}, 13);
        Assert.assertEquals(18, res);
    }
}