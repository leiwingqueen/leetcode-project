package com.liyongquan.dfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class IntegerReplacementTest {
    private IntegerReplacement ir = new IntegerReplacement();

    @Test
    public void integerReplacement4() {
        int res = ir.integerReplacement4(7);
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}