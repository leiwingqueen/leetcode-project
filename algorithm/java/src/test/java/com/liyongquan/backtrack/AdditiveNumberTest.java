package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class AdditiveNumberTest {
    private AdditiveNumber number = new AdditiveNumber();

    @Test
    public void isAdditiveNumber() {
        boolean res = number.isAdditiveNumber("123");
        Assert.assertEquals(true, res);
    }
}