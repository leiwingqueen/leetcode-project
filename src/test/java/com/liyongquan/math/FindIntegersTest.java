package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/9/11
 */
@Slf4j
public class FindIntegersTest {
    private FindIntegers fi = new FindIntegers();

    @Test
    public void findIntegers() {
        int res = fi.findIntegers(5);
        Assert.assertEquals(5, res);
    }

    @Test
    public void findIntegers3() {
        int res = fi.findIntegers3(3);
        Assert.assertEquals(3, res);
    }
}