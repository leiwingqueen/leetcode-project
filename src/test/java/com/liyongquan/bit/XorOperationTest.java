package com.liyongquan.bit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class XorOperationTest {
    XorOperation ox = new XorOperation();

    @Test
    public void xorOperation() {
        int res = ox.xorOperation(4, 3);
        log.info("{}", res);
    }
}