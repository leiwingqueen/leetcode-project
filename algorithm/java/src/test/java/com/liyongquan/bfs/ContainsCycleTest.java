package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

@Slf4j
public class ContainsCycleTest {
    private ContainsCycle cc = new ContainsCycle();

    @Test
    public void containsCycle() {
        char[][] grid = {{'b', 'a', 'c'}, {'c', 'a', 'c'}, {'d', 'd', 'c'}, {'b', 'c', 'c'}};
        boolean res = cc.containsCycle(grid);
        Assert.assertEquals(false, res);
    }
}