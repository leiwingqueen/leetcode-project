package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class AllOne2Test {
    @Test
    public void inc() {
        AllOne2 allOne2 = new AllOne2();
        allOne2.inc("a");
        allOne2.inc("b");
        allOne2.inc("b");
        allOne2.inc("c");
        allOne2.inc("c");
        allOne2.inc("c");
        allOne2.dec("b");
        allOne2.dec("b");
        allOne2.dec("a");
        String minKey = allOne2.getMinKey();
        Assert.assertEquals("c", minKey);
    }
}