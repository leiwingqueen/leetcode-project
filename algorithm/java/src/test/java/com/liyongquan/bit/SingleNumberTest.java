package com.liyongquan.bit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SingleNumberTest {
    private SingleNumber sn = new SingleNumber();

    @Test
    public void singleNumber() {
        int res = sn.singleNumber2(new int[]{-2, -2, 1, 1, 4, 1, 4, 4, -4, -2});
        log.info("{}", res);
    }

    @Test
    public void test() {
        int num = -2;
        for (int i = 0; i < 32; i++) {
            int bit1 = (num >> i) & 1;
            int bit2 = (num & (1 << i)) != 0 ? 1 : 0;
            log.info("i:{},bit1:{},bit2:{}", i, bit1, bit2);
        }
    }
}