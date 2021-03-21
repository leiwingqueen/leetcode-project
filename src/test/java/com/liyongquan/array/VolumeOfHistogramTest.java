package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class VolumeOfHistogramTest {
    private VolumeOfHistogram vh = new VolumeOfHistogram();

    @Test
    public void trap() {
        int res = vh.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        log.info("{}", res);
        Assert.assertEquals(6, res);
    }
}