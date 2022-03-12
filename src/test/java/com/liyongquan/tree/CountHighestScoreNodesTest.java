package com.liyongquan.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountHighestScoreNodesTest {
    private CountHighestScoreNodes scoreNodes = new CountHighestScoreNodes();

    /**
     * [-1,2,0,2,0]
     */
    @Test
    public void countHighestScoreNodes() {
        int res = scoreNodes.countHighestScoreNodes(new int[]{-1, 2, 0, 2, 0});
        Assert.assertEquals(3, res);
    }
}