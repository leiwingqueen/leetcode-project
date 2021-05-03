package com.liyongquan.interview;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CoinCountTest {
    private CoinCount coinCount = new CoinCount();

    @Test
    public void coinCount3() {
        int res = coinCount.coinCount3(5, 5);
        log.info("{}", res);
    }
}