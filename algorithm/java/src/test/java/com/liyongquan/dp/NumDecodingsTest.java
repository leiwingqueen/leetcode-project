package com.liyongquan.dp;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumDecodingsTest {
    private NumDecodings nd = new NumDecodings();

    @Test
    public void numDecodings() {
        int i = nd.numDecodings("00");
        Assert.assertEquals(0, i);
    }

    @Test
    public void numDecodings_2() {
        int i = nd.numDecodings("10");
        Assert.assertEquals(1, i);
    }

    @Test
    public void numDecodings_3() {
        int i = nd.numDecodings("01");
        Assert.assertEquals(0, i);
    }

    @Test
    public void numDecodings_4() {
        int i = nd.numDecodings("17");
        Assert.assertEquals(2, i);
    }
}