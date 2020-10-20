package com.liyongquan.dfs;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NQueens2Test {
    private NQueens2 nQueens2 = new NQueens2();

    @Test
    public void totalNQueens() {
        int i = nQueens2.totalNQueens(4);
        Assert.assertEquals(2, i);
    }
}