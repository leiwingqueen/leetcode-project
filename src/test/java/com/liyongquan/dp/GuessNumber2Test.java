package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class GuessNumber2Test {
    @Test
    public void getMoneyAmount() {
        GuessNumber2 gn = new GuessNumber2();
        int res2 = gn.getMoneyAmount4(10);
        log.info("res:{}", res2);
        Assert.assertEquals(16, res2);
    }
}