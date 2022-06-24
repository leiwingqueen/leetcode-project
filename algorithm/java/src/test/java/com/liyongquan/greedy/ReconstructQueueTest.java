package com.liyongquan.greedy;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReconstructQueueTest {
    private ReconstructQueue rq = new ReconstructQueue();

    @Test
    public void reconstructQueue() {
        int[][] queue = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        int[][] res = rq.reconstructQueue(queue);
    }
}