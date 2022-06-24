package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class DailyTemperaturesTest {
    private DailyTemperatures dt = new DailyTemperatures();

    @Test
    public void dailyTemperatures2() {
        int[] res = dt.dailyTemperatures2(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        StringBuilder sb = new StringBuilder();
        for (int re : res) {
            sb.append(re + ",");
        }
        log.info("{}", sb.toString());
    }
}