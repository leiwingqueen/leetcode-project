package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LastStoneWeightIITest {
    private LastStoneWeightII lsw = new LastStoneWeightII();

    @Test
    public void lastStoneWeightII() {
        int res = lsw.lastStoneWeightII2(new int[]{2, 7, 4, 1, 8, 1});
        log.info("{}", res);
    }
}