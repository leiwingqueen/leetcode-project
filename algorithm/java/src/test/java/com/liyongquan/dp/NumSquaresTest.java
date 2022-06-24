package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NumSquaresTest {
    private NumSquares ns = new NumSquares();

    @Test
    public void numSquares() {
        int i = ns.numSquares(12);
        log.info("{}", i);
    }
}