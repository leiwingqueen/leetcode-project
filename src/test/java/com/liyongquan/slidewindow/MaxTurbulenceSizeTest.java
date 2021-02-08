package com.liyongquan.slidewindow;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;

@Slf4j
public class MaxTurbulenceSizeTest extends TestCase {
    private MaxTurbulenceSize mt = new MaxTurbulenceSize();

    public void testMaxTurbulenceSize() {
        int res = mt.maxTurbulenceSize(new int[]{0, 1, 1, 0, 1, 0, 1, 1, 0, 0});
        log.info("{}", res);
        Assert.assertEquals(5, res);
    }
}