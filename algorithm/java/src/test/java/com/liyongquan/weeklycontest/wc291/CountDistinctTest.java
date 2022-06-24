package com.liyongquan.weeklycontest.wc291;

import com.liyongquan.weeklycontest.wc281.CoutPairs;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountDistinctTest {
    private CountDistinct countDistinct = new CountDistinct();

    /**
     * [2,3,3,2,2]
     * 2
     * 2
     */
    @Test
    public void countDistinct() {
        int res = countDistinct.countDistinct2(new int[]{2, 3, 3, 2, 2}, 2, 2);
        Assert.assertEquals(11, res);
    }
}