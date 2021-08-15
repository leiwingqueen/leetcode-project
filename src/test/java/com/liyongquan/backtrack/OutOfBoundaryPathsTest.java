package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/15
 */
@Slf4j
public class OutOfBoundaryPathsTest {
    private OutOfBoundaryPaths paths = new OutOfBoundaryPaths();

    /**
     * 2
     * 2
     * 2
     * 0
     * 0
     */
    @Test
    public void findPaths() {
        int res = this.paths.findPaths(2, 2, 2, 0, 0);
        log.info("{}", res);
        Assert.assertEquals(6, res);
    }
}