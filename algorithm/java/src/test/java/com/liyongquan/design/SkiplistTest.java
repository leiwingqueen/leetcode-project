package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SkiplistTest {

    @Test
    public void search() {
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        skiplist.add(2);
        skiplist.add(3);
        Assert.assertEquals(false, skiplist.search(0));
        skiplist.add(4);
        Assert.assertEquals(true, skiplist.search(1));
        Assert.assertEquals(false, skiplist.erase(0));
        Assert.assertEquals(true, skiplist.erase(1));
        Assert.assertEquals(false, skiplist.erase(1));
    }
}