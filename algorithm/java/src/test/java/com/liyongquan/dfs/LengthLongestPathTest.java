package com.liyongquan.dfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LengthLongestPathTest {
    private LengthLongestPath lp = new LengthLongestPath();

    @Test
    public void lengthLongestPath() {
        int res = lp.lengthLongestPath("a");
        Assert.assertEquals(0, res);
    }
}