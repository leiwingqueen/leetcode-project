package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SuperEggDropTest {
    private SuperEggDrop sed = new SuperEggDrop();

    @Test
    public void superEggDrop() {
        int res = sed.superEggDrop(2, 6);
        log.info("{}", res);
        Assert.assertEquals(3, res);
    }
}