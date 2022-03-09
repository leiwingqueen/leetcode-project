package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class BestRotationTest {
    private BestRotation br = new BestRotation();

    @Test
    public void bestRotation() {
        int res = br.bestRotation(new int[]{2, 3, 1, 4, 0});
        Assert.assertEquals(3, res);
    }

    @Test
    public void test2() {
        int res = br.bestRotation2(new int[]{2, 4, 1, 3, 0});
        Assert.assertEquals(0, res);
    }
}