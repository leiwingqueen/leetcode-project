package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class PacificAtlanticTest {
    private PacificAtlantic atlantic = new PacificAtlantic();

    @Test
    public void pacificAtlantic() {
        int[][] grids = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        List<List<Integer>> res = atlantic.pacificAtlantic(grids);
        for (List<Integer> re : res) {
            log.info("[{},{}]", re.get(0), re.get(1));
        }
    }
}