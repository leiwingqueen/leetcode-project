package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class GetMaxMatrixTest {
    private GetMaxMatrix gm = new GetMaxMatrix();

    @Test
    public void getMaxMatrix2() {
        int[][] matrix = {
                {9, -8, 1, 3, -2},
                {-3, 7, 6, -2, 4},
                {6, -4, -4, 8, -7}
        };
        int[] res = gm.getMaxMatrix2(matrix);
        for (int re : res) {
            log.info("{}", re);
        }
    }
}