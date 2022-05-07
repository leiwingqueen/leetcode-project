package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

@Slf4j
public class MinMutationTest {
    private MinMutation minMutation = new MinMutation();

    /**
     * "AACCGGTT"
     * "AACCGGTA"
     * ["AACCGGTA"]
     */
    @Test
    public void minMutation() {
        int res = minMutation.minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"});
        Assert.assertEquals(1, res);
    }

    /**
     * "AAAACCCC"
     * "CCCCCCCC"
     * ["AAAACCCA","AAACCCCA","AACCCCCA","AACCCCCC","ACCCCCCC","CCCCCCCC","AAACCCCC","AACCCCCC"]
     */
    @Test
    public void test2() {
        int res = minMutation.minMutation("AAAACCCC", "CCCCCCCC", new String[]{"AAAACCCA", "AAACCCCA", "AACCCCCA", "AACCCCCC", "ACCCCCCC", "CCCCCCCC", "AAACCCCC", "AACCCCCC"});
        Assert.assertEquals(4, res);
    }
}