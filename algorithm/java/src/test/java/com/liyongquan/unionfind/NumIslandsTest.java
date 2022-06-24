package com.liyongquan.unionfind;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NumIslandsTest {
    private NumIslands ni = new NumIslands();

    @Test
    public void numIslands() {
        char[][] arr = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        int res = ni.numIslands(arr);
        log.info("res:{}", res);
        Assert.assertEquals(1, res);
    }
}