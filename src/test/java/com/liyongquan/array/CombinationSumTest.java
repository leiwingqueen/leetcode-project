package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class CombinationSumTest {
    private CombinationSum cs = new CombinationSum();

    /**
     * [2,3,6,7]
     * 7
     */
    @Test
    public void combinationSum() {
        List<List<Integer>> res = cs.combinationSum(new int[]{2, 3, 6, 7}, 7);
        for (List<Integer> re : res) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : re) {
                sb.append(i + ",");
            }
            log.info("{}", sb.toString());
        }
    }
}