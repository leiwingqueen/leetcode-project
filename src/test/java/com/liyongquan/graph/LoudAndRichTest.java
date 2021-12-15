package com.liyongquan.graph;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/12/15
 */
@Slf4j
public class LoudAndRichTest {
    private LoudAndRich lar = new LoudAndRich();

    //[[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]]
    //[3,2,5,4,6,1,7,0]
    @Test
    public void loudAndRich() {
        int[][] richer = {
                {1, 0},
                {2, 1},
                {3, 1},
                {3, 7},
                {4, 3},
                {5, 3},
                {6, 3}
        };
        int[] res = lar.loudAndRich(richer, new int[]{3, 2, 5, 4, 6, 1, 7, 0});
        for (int re : res) {
            log.warn("{}", re);
        }
    }
}