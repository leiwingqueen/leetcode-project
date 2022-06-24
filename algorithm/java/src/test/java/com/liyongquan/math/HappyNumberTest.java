package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class HappyNumberTest {
    private HappyNumber hn = new HappyNumber();

    @Test
    public void isHappy() {
        boolean res = hn.isHappy(19);
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }
}