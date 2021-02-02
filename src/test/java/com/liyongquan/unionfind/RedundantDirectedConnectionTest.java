package com.liyongquan.unionfind;

import lombok.extern.slf4j.Slf4j;
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
    }
}