package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/9/20
 */
@Slf4j
public class FindNumberOfLISTest {
    private FindNumberOfLIS lis = new FindNumberOfLIS();

    @Test
    public void findNumberOfLIS() {
        int res = lis.findNumberOfLIS(new int[]{2, 2, 2, 2, 2});
        Assert.assertEquals(5, res);
    }

    @Test
    public void test() {
        int res = lis.findNumberOfLIS(new int[]{1});
        Assert.assertEquals(1, res);
    }

    @Test
    public void test2() {
        int res = lis.findNumberOfLIS(new int[]{1, 2});
        Assert.assertEquals(1, res);
    }
}