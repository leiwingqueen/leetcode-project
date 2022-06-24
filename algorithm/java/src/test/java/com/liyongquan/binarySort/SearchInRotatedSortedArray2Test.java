package com.liyongquan.binarySort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SearchInRotatedSortedArray2Test {
    private SearchInRotatedSortedArray2 sr = new SearchInRotatedSortedArray2();

    @Test
    public void search() {
        boolean res = sr.search(new int[]{2, 2, 2, 3, 2, 2, 2}, 3);
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }

    @Test
    public void test() {
        boolean res = sr.search(new int[]{1, 0, 1, 1, 1}, 0);
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }

    @Test
    public void test2() {
        boolean res = sr.search(new int[]{1, 3, 5}, 1);
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }

    @Test
    public void test3() {
        boolean res = sr.search2(new int[]{2, 2, 2, 3, 1}, 1);
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }
}