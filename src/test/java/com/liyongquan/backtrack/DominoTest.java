package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class DominoTest {
    private Domino domino = new Domino();

    /**
     * 2
     * 3
     * [[1, 0], [1, 1]]
     */
    @Test
    public void domino() {
        int res = this.domino.domino2(2, 3, new int[][]{
                {1, 0},
                {1, 1}
        });
        Assert.assertEquals(2, res);
    }

    /**
     * 8
     * 8
     * []
     */
    @Test
    public void test2() {
        int res = domino.domino2(8, 8, new int[][]{});
        Assert.assertEquals(0, res);
    }
}