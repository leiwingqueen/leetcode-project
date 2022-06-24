package com.liyongquan.unionfind;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class RedundantDirectedConnectionTest {
    private RedundantDirectedConnection rdc = new RedundantDirectedConnection();

    @Test
    public void findRedundantDirectedConnection() {
        int[][] edges = {{2, 1}, {3, 1}, {4, 2}, {1, 4}};
        int[] res = rdc.findRedundantDirectedConnection(edges);
        StringBuilder sb = new StringBuilder();
        for (int re : res) {
            sb.append(re + ",");
        }
        log.info(sb.toString());
        Assert.assertArrayEquals(new int[]{2, 1}, res);
    }

    /**
     * [[1,2],[1,3],[2,3]]
     */
    @Test
    public void test() {
        int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        int[] res = rdc.findRedundantDirectedConnection(edges);
        StringBuilder sb = new StringBuilder();
        for (int re : res) {
            sb.append(re + ",");
        }
        log.info(sb.toString());
    }

    /**
     * [[1,2],[2,3],[3,4],[4,1],[1,5]]
     */
    @Test
    public void test2() {
        int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 5}};
        int[] res = rdc.findRedundantDirectedConnection(edges);
        StringBuilder sb = new StringBuilder();
        for (int re : res) {
            sb.append(re + ",");
        }
        log.info(sb.toString());
        Assert.assertArrayEquals(new int[]{4, 1}, res);
    }
}