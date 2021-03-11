package com.liyongquan.binarySort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SearchRotatedSortedArrayTest {
    private SearchRotatedSortedArray sr = new SearchRotatedSortedArray();

    @Test
    public void search2() {
        int res = sr.search2(new int[]{1, 3}, 3);
        log.info("{}", res);
        Assert.assertEquals(1, res);
    }
}