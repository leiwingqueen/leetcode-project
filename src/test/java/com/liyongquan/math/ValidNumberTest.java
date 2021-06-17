package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ValidNumberTest {
    private ValidNumber vn = new ValidNumber();

    @Test
    public void isNumber() {
        boolean res = vn.isNumber("e");
        log.info("{}", res);
        Assert.assertEquals(false, res);
    }
}