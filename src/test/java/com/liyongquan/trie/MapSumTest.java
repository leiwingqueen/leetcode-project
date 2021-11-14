package com.liyongquan.trie;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/11/14
 */
@Slf4j
public class MapSumTest {

    @Test
    public void insert() {
        MapSum ms = new MapSum();
        ms.insert("apple", 3);
        int r = ms.sum("ap");
        Assert.assertEquals(3, r);
    }
}