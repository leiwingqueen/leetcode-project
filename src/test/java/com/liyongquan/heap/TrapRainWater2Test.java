package com.liyongquan.heap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class TrapRainWater2Test {
    private TrapRainWater2 tp = new TrapRainWater2();

    @Test
    public void trapRainWater() {
        int[][] array = {{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}};
        int i = tp.trapRainWater2(array);
        log.info("{}", i);
        Assert.assertEquals(4, i);
    }
}