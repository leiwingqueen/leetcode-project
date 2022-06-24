package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class SubsetsWithDupTest {
    private SubsetsWithDup swd = new SubsetsWithDup();

    @Test
    public void subsetsWithDup() {
        List<List<Integer>> res = swd.subsetsWithDup(new int[]{1, 2, 2});
        for (List<Integer> list : res) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : list) {
                sb.append(i + ",");
            }
            log.info("{}", sb.toString());
        }
    }
}