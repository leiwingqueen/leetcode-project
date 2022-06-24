package com.liyongquan.weeklycontest.wc236;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FindTheWinnerTest {
    private FindTheWinner fw = new FindTheWinner();

    @Test
    public void findTheWinner() {
        int res = fw.findTheWinner(6, 5);
        Assert.assertEquals(1, res);
    }
}