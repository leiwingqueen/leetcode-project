package com.liyongquan.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RemoveKdigitsTest {
    private RemoveKdigits rk = new RemoveKdigits();

    @Test
    public void removeKdigits() {
        String res = rk.removeKdigits("1432219", 3);
        log.info("{}", res);
        Assert.assertEquals("1219", res);
    }
}