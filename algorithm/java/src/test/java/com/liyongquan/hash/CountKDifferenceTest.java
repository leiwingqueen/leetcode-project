package com.liyongquan.hash;

import com.liyongquan.fsm.CountValidWords;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountKDifferenceTest {
    private CountKDifference difference = new CountKDifference();

    //[1,2,2,1]
    //1
    @Test
    public void countKDifference() {
        int res = difference.countKDifference(new int[]{1, 2, 2, 1}, 1);
        Assert.assertEquals(4, res);
    }
}