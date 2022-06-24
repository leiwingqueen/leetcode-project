package com.liyongquan.weeklycontest.wc286;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinDeletionTest {
    private MinDeletion md = new MinDeletion();

    @Test
    public void minDeletion() {
        int res = md.minDeletion(new int[]{1, 1, 2, 3, 5});
        Assert.assertEquals(1, res);
    }

    @Test
    public void test() {
        int[] arr = {1, 1, 2, 2, 3, 3};
        int res = md.minDeletion(arr);
        Assert.assertEquals(2, res);
    }
}