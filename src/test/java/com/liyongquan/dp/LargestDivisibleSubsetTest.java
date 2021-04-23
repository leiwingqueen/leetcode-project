package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class LargestDivisibleSubsetTest {
    private LargestDivisibleSubset lds = new LargestDivisibleSubset();

    @Test
    public void largestDivisibleSubset() {
        List<Integer> res = lds.largestDivisibleSubset(new int[]{2, 10000, 10000001});
        for (Integer re : res) {
            log.info("{}", re);
        }
    }
}