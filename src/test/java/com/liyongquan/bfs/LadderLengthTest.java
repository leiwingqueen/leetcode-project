package com.liyongquan.bfs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

@Slf4j
public class LadderLengthTest {
    private LadderLength ll = new LadderLength();

    /**
     * "hit"
     * "cog"
     * ["hot","dot","dog","lot","log","cog"]
     */
    @Test
    public void ladderLength() {
        int res = ll.ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        log.info("{}", res);
        Assert.assertEquals(5, res);
    }
}