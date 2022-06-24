package com.liyongquan.slidewindow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class FindUnsortedSubarrayTest {
    private FindUnsortedSubarray fu = new FindUnsortedSubarray();

    /**
     * [1,3,2,4,5]
     */
    @Test
    public void findUnsortedSubarray() {
        int res = fu.findUnsortedSubarray2(new int[]{1, 3, 2, 4, 5});
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }
}