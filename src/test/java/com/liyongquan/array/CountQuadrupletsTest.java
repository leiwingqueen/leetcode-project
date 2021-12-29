package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountQuadrupletsTest {
    private CountQuadruplets cq = new CountQuadruplets();

    @Test
    public void countQuadruplets() {
        int[] arr = {
                28, 8, 49, 85, 37, 90, 20, 8
        };
        int res = cq.countQuadruplets(arr);
        Assert.assertEquals(1, res);
    }
}