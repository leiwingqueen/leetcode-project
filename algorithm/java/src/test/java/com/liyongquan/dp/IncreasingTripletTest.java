package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class IncreasingTripletTest {
    private IncreasingTriplet it = new IncreasingTriplet();

    @Test
    public void increasingTriplet() {
        boolean res = it.increasingTriplet(new int[]{5, 4, 3, 2, 1});
        Assert.assertEquals(false, res);
    }
}