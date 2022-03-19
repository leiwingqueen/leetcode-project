package com.liyongquan.weeklycontest.wc273;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RecoverArrayTest {
    private RecoverArray recoverArray = new RecoverArray();

    @Test
    public void recoverArray() {
        int[] res = recoverArray.recoverArray(new int[]{2, 10, 6, 4, 8, 12});
        for (int re : res) {
            log.info("{}", re);
        }
    }
}