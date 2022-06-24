package com.liyongquan.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SimplifyPathTest {
    private SimplifyPath sp = new SimplifyPath();

    @Test
    public void simplifyPath() {
        String res = sp.simplifyPath("/home/");
        log.info("{}", res);
        Assert.assertEquals("/home", res);
    }
}