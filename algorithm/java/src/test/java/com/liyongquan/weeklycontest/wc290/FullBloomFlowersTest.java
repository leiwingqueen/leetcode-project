package com.liyongquan.weeklycontest.wc290;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class FullBloomFlowersTest {
    private FullBloomFlowers flowers = new FullBloomFlowers();

    //[[1,6],[3,7],[9,12],[4,13]]
    //[2,3,7,11]
    @Test
    public void fullBloomFlowers() {
        int[] res = flowers.fullBloomFlowers(new int[][]{
                {1, 6}, {3, 7}, {9, 12}, {4, 13}
        }, new int[]{2, 3, 7, 11});
        log.info("{}", res);
        Assert.assertArrayEquals(new int[]{1, 2, 2, 2}, res);
    }
}