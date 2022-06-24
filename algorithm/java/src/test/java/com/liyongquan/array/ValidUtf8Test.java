package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ValidUtf8Test {
    private ValidUtf8 validUtf8 = new ValidUtf8();

    @Test
    public void validUtf8() {
        boolean res = validUtf8.validUtf8(new int[]{197, 130, 1});
        Assert.assertEquals(true, res);
    }
}