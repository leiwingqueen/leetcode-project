package com.liyongquan.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CalculateTest {

    @Test
    public void calculate() {
        Calculate cal = new Calculate();
        int calculate = cal.calculate("3+2*2");
        log.info("res:{}", calculate);
    }
}