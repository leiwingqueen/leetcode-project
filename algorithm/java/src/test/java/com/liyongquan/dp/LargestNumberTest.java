package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LargestNumberTest {
    private LargestNumber ln = new LargestNumber();

    /**
     * [4,3,2,5,6,7,2,5,5]
     * 9
     */
    @Test
    public void largestNumber() {
        String res = ln.largestNumber(new int[]{4, 3, 2, 5, 6, 7, 2, 5, 5}, 9);
        log.info("{}", res);
        Assert.assertEquals("7772", res);
    }

    /**
     * [6,10,15,40,40,40,40,40,40]
     * 47
     */
    @Test
    public void test2() {
        String res = ln.largestNumber(new int[]{6, 10, 15, 40, 40, 40, 40, 40, 40}, 47);
        log.info("{}", res);
        Assert.assertEquals("32211", res);
    }

    /**
     * [2,4,6,2,4,6,4,4,4]
     * 5
     * <p>
     * [6,10,15,40,40,40,40,40,40]
     * 47
     */
    @Test
    public void test3() {
        String res = ln.largestNumber(new int[]{6, 10, 15, 40, 40, 40, 40, 40, 40}, 47);
        log.info("{}", res);
    }
}