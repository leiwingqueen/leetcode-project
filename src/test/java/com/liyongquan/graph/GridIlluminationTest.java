package com.liyongquan.graph;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class GridIlluminationTest {
    private GridIllumination illumination = new GridIllumination();

    //5
    //[[0,0],[0,4]]
    //[[0,4],[0,1],[1,4]]
    @Test
    public void gridIllumination() {
        int[] res = illumination.gridIllumination(5, new int[][]{
                {0, 0},
                {0, 4}
        }, new int[][]{
                {0, 4},
                {0, 1},
                {1, 4}
        });
        for (int re : res) {
            log.info("{}", re);
        }
    }
}