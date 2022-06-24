package com.liyongquan.bit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaximizeXorTest {
    private MaximizeXor maximizeXor = new MaximizeXor();

    /**
     * [0,1,2,3,4]
     * [[3,1],[1,3],[5,6]]
     */
    @Test
    public void maximizeXor() {
        int[] res = maximizeXor.maximizeXor(new int[]{0, 1, 2, 3, 4}, new int[][]{
                {3, 1},
                {1, 3},
                {5, 6}
        });
        for (int re : res) {
            log.info("{}", re);
        }
    }
}