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
public class MinStepsTest {
    private MinSteps minSteps = new MinSteps();

    @Test
    public void minSteps() {
        int res = minSteps.minSteps(3);
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}