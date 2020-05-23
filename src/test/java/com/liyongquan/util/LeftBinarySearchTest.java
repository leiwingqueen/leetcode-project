package com.liyongquan.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LeftBinarySearchTest {
    private IBinarySearch search = new LeftBinarySearch();

    @Test
    public void search() {
        int search = this.search.search(new int[]{1, 2, 2, 7, 8}, 2);
        Assert.assertEquals(1, search);
    }
}