package com.liyongquan.weeklycontest.spring2021;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class PurchasePlansTest {
    private PurchasePlans pl = new PurchasePlans();

    @Test
    public void purchasePlans() {
        int res = pl.purchasePlans(new int[]{2, 5, 3, 5}, 6);
        log.info("{}", res);
    }
}