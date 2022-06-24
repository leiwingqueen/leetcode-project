package com.liyongquan.heap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class SkylineTest {
    private Skyline skyline = new Skyline();

    @Test
    public void getSkyline() {
        int[][] args = {
                {2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}
        };
        List<List<Integer>> res = this.skyline.getSkyline(args);
        for (List<Integer> arr : res) {
            log.info("[{},{}]", arr.get(0), arr.get(1));
        }
    }
}