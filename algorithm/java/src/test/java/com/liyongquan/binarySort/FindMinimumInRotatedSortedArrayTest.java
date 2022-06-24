package com.liyongquan.binarySort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class FindMinimumInRotatedSortedArrayTest {
    private FindMinimumInRotatedSortedArray fm = new FindMinimumInRotatedSortedArray();

    @Test
    public void findMin() {
        int res = fm.findMin(new int[]{2, 1});
        log.info("{}", res);
        Assert.assertEquals(1, res);
    }
}