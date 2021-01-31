package com.liyongquan.unionfind;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class PseudoCriticalEdgesTest {
    private PseudoCriticalEdges pc = new PseudoCriticalEdges();

    @Test
    public void findCriticalAndPseudoCriticalEdges() {
        int[][] edges = {
                {0, 1, 1},
                {1, 2, 1},
                {2, 3, 2},
                {0, 3, 2},
                {0, 4, 3},
                {3, 4, 3},
                {1, 4, 6}
        };
        List<List<Integer>> res = pc.findCriticalAndPseudoCriticalEdges2(5, edges);
        StringBuilder sb = new StringBuilder();
        for (Integer integer : res.get(0)) {
            sb.append(integer + ",");
        }
        log.info(sb.toString());
        sb = new StringBuilder();
        for (Integer i : res.get(1)) {
            sb.append(i + ",");
        }
        log.info(sb.toString());
    }
}