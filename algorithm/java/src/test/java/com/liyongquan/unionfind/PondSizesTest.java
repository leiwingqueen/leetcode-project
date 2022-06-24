package com.liyongquan.unionfind;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class PondSizesTest {
    private PondSizes ps = new PondSizes();

    /**
     * //[
     * //  [0,2,1,0],
     * //  [0,1,0,1],
     * //  [1,1,0,1],
     * //  [0,1,0,1]
     * //]
     */
    @Test
    public void pondSizes() {
        int[][] matrix = {
                {0, 2, 1, 0},
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 1, 0, 1},
        };
        int[] res = ps.pondSizes(matrix);
        for (int re : res) {
            log.info("{}", re);
        }
    }
}