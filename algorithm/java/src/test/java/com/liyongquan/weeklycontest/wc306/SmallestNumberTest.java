package com.liyongquan.weeklycontest.wc306;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SmallestNumberTest {
    private SmallestNumber smallestNumber = new SmallestNumber();

    @Test
    public void smallestNumber() {
        String res = smallestNumber.smallestNumber("IIIDIDDD");
        Assert.assertEquals("123549876", res);
    }
}