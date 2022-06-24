package com.liyongquan.weeklycontest.wc241;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MinSwapsTest {
    private MinSwaps minSwaps = new MinSwaps();

    @Test
    public void minSwaps() {
        int res = minSwaps.minSwaps("010");
        log.info("{}", res);
    }
}