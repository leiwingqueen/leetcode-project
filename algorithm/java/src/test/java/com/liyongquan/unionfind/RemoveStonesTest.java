package com.liyongquan.unionfind;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Slf4j
public class RemoveStonesTest {
    private RemoveStones rs = new RemoveStones();

    @Test
    public void removeStones() {
        int[][] array = {
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 2},
                {2, 1},
                {2, 2}
        };
        int res = rs.removeStones(array);
        log.info("res:{}", res);
        assertEquals(5, res);
    }
}