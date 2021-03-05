package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class LongestConsecutiveTest {
    private LongestConsecutive lc = new LongestConsecutive();

    @Test
    public void test() {
        int res = lc.longestConsecutive2(new int[]{100, 4, 200, 1, 3, 2});
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }

    /**
     * [0,3,7,2,5,8,4,6,0,1]
     */
    @Test
    public void test2() {
        int res = lc.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1});
        log.info("{}", res);
        Assert.assertEquals(9, res);
    }
}