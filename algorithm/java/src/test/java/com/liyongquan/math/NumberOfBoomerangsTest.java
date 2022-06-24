package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/9/13
 */
@Slf4j
public class NumberOfBoomerangsTest {
    private NumberOfBoomerangs boomerangs = new NumberOfBoomerangs();

    @Test
    public void numberOfBoomerangs() {
        int[][] args = {
                {0, 0}, {1, 0}, {2, 0}
        };
        int res = boomerangs.numberOfBoomerangs(args);
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }
}