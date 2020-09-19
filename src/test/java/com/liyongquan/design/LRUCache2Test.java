package com.liyongquan.design;

import com.liyongquan.array.Card;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LRUCache2Test {
    /**
     * ["LRUCache","get","put","get","put","put","get","get"]
     * [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
     */
    @Test
    public void test1() {
        LRUCache2 cache = new LRUCache2(2);
        int i = cache.get(2);
        Assert.assertEquals(-1, i);
        int i2 = cache.get(2);
        Assert.assertEquals(-1, i2);
        cache.put(2, 6);
        int i1 = cache.get(1);
        Assert.assertEquals(-1, i1);
        cache.put(1, 5);
        cache.put(1, 2);
        int i3 = cache.get(1);
        Assert.assertEquals(2, i3);
        int i4 = cache.get(2);
        Assert.assertEquals(6, i4);
    }
}