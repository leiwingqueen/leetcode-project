package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ClumsyTest {
    private Clumsy clumsy = new Clumsy();

    @Test
    public void clumsy() {
        int res = this.clumsy.clumsy(10);
        log.info("{}", res);
    }
}