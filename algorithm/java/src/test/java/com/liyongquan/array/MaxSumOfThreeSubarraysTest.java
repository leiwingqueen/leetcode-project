package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/12/8
 */
@Slf4j
public class MaxSumOfThreeSubarraysTest {
    private MaxSumOfThreeSubarrays subarrays = new MaxSumOfThreeSubarrays();

    @Test
    public void maxSumOfThreeSubarrays() {
        int[] res = subarrays.maxSumOfThreeSubarrays(new int[]{1, 2, 1, 2, 6, 7, 5, 1}, 2);
        for (int re : res) {
            log.info("{}", re);
        }
    }
}