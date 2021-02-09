package com.liyongquan.slidewindow;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubarraysWithKDistinctTest extends TestCase {
    private SubarraysWithKDistinct sw = new SubarraysWithKDistinct();

    public void testAtMost() {
        int res = sw.atMost(new int[]{1, 3, 2, 3}, 3);
        log.info("{}", res);
    }
}