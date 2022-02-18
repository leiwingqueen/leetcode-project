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
        int res = this.domino.domino(2, 3, new int[][]{
                {1, 0},
                {1, 1}
        });
        Assert.assertEquals(2, res);
    }
}