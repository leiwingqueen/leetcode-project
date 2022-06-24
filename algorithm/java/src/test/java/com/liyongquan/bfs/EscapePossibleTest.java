package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class EscapePossibleTest {
    private EscapePossible ep = new EscapePossible();

    /**
     * [[0,1],[1,0]]
     * [0,0]
     * [0,2]
     * <p>
     * [[10,9],[9,10],[10,11],[11,10]]
     * [0,0]
     * [10,10]
     */
    @Test
    public void isEscapePossible() {
        int[][] blocks = {
                {10, 9}, {9, 10}, {10, 11}, {11, 10}
        };
        boolean res = ep.isEscapePossible(blocks, new int[]{0, 0}, new int[]{10, 10});
        log.info("{}", res);
        Assert.assertEquals(false, res);
    }

    //[]
    //[0,0]
    //[999999,999999]
    @Test
    public void isEscapePossible2() {
        int[][] blocks = {
        };
        boolean res = ep.isEscapePossible(blocks, new int[]{0, 0}, new int[]{999999,999999});
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }
}