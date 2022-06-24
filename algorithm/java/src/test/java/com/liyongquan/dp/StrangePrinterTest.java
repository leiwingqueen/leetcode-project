package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class StrangePrinterTest {
    private StrangePrinter sp = new StrangePrinter();

    @Test
    public void strangePrinter() {
        int res = sp.strangePrinter("aba");
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }
}