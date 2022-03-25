package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class XiaoxiaoleTest {
    private Xiaoxiaole xl = new Xiaoxiaole();

    @Test
    public void scan() {
        int[][] grid = {
                {1, 2, 3, 2, 1},
                {1, 1, 2, 2, 1},
                {2, 2, 3, 3, 2},
                {1, 1, 1, 2, 1},
        };
        xl.scan(grid, 3);
        for (int[] g : grid) {
            log.info("{}", g);
        }
    }
}