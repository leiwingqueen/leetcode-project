package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/16
 */
@Slf4j
public class CountArrangementTest {
    private CountArrangement arrangement = new CountArrangement();

    @Test
    public void countArrangement() {
        int res = arrangement.countArrangement(2);
        Assert.assertEquals(2, res);
    }
}