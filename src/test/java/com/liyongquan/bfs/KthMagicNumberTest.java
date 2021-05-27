package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class KthMagicNumberTest {
    private KthMagicNumber kthMagicNumber = new KthMagicNumber();

    @Test
    public void getKthMagicNumber() {
        int res = this.kthMagicNumber.getKthMagicNumber(251);
        log.info("{}", res);
    }
}