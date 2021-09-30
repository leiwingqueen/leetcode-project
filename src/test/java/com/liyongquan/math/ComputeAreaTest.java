package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/9/30
 */
@Slf4j
public class ComputeAreaTest {
    private ComputeArea computeArea = new ComputeArea();

    //-2
    //-2
    //2
    //2
    //2
    //-2
    //4
    //2
    @Test
    public void computeArea() {
        int res = computeArea.computeArea(-3, 0, 3, 4
                , 0, -1, 9, 2);
        log.info("{}", res);
        Assert.assertEquals(45, res);
    }

    @Test
    public void test2() {
        int res = computeArea.computeArea(-2, -2, 2, 2,
                2, -2, 4, 2);
        log.info("{}", res);
        Assert.assertEquals(24, res);
    }

    @Test
    public void test3() {
        int res = computeArea.computeArea(-2, -2, 2, 2,
                -3, -3, 3, -1);
        Assert.assertEquals(24, res);
    }
}