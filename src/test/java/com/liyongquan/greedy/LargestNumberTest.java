package com.liyongquan.greedy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LargestNumberTest {
    private LargestNumber ln = new LargestNumber();

    @Test
    public void largestNumber() {
        String res = ln.largestNumber(new int[]{111311, 1113});
        log.info("{}", res);
        //1113 11 1113
        //1113 111311
        Assert.assertEquals("1113111311", res);
    }
}