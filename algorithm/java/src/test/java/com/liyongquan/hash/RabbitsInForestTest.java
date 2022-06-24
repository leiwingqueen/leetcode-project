package com.liyongquan.hash;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RabbitsInForestTest {
    private RabbitsInForest rif = new RabbitsInForest();

    @Test
    public void numRabbits() {
        int res = rif.numRabbits(new int[]{1, 0, 1, 0, 0});
        log.info("{}", res);
    }
}