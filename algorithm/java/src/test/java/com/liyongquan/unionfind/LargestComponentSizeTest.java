package com.liyongquan.unionfind;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LargestComponentSizeTest {
    private LargestComponentSize componentSize = new LargestComponentSize();

    @Test
    public void largestComponentSize() {
        int res = componentSize.largestComponentSize(new int[]{4, 6, 15, 35});
        Assert.assertEquals(4, res);
    }
}