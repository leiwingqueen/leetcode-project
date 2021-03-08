package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class CombinationSum2Test {
    private CombinationSum2 cs = new CombinationSum2();

    @Test
    public void combinationSum2() {
        List<List<Integer>> res = cs.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        for (List<Integer> re : res) {
            StringBuilder sb = new StringBuilder();
            for (Integer in : re) {
                sb.append(in + ",");
            }
            log.info("{}", sb.toString());
        }
    }
}