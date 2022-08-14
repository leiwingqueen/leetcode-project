package com.liyongquan.weeklycontest.wc306;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class EdgeScoreTest {
    private EdgeScore score = new EdgeScore();

    @Test
    public void edgeScore() {
        int res = score.edgeScore(new int[]{2, 0, 0, 2});
        Assert.assertEquals(0, res);
    }
}