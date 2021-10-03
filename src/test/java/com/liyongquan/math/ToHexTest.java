package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/10/2
 */
@Slf4j
public class ToHexTest {
    private ToHex toHex = new ToHex();

    @Test
    public void toHex() {
        String s = toHex.toHex(26);
        Assert.assertEquals("1a", s);
    }

    @Test
    public void toHex2() {
        String s = toHex.toHex(-1);
        Assert.assertEquals("ffffffff", s);
    }
}