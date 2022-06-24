package com.liyongquan.greedy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CandyTest {
    private Candy candy = new Candy();

    @Test
    public void candy2() {
        int res = candy.candy2(new int[]{1, 2, 2});
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}