package com.liyongquan.weeklycontest.bwc69;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class PossibleToStampTest {
    private PossibleToStamp pts = new PossibleToStamp();

    //[[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]]
    //4
    //3
    @Test
    public void possibleToStamp2() {
        int[][] matrix = {
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0}
        };
        boolean res = pts.possibleToStamp2(matrix, 4, 3);
        Assert.assertEquals(true, res);
    }
}