package com.liyongquan.greedy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class EatenApplesTest {
    private EatenApples ea = new EatenApples();

    @Test
    public void eatenApples() {
        int[] apples = {1, 2, 3, 5, 2};
        int[] days = {3, 2, 1, 4, 2};
        int r = ea.eatenApples(apples, days);
        Assert.assertEquals(7, r);
    }
}