package com.liyongquan.slidewindow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/10
 */
@Slf4j
public class NumberOfArithmeticSlicesTest {
    private NumberOfArithmeticSlices slices = new NumberOfArithmeticSlices();

    @Test
    public void numberOfArithmeticSlices() {
        int res = slices.numberOfArithmeticSlices(new int[]{1, 2, 3, 8, 9, 10});
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}