package com.liyongquan.util;


import org.junit.Assert;
import org.junit.Test;

public class IBinarySearchTest {
    private IBinarySearch binarySearch = new IBinarySearchImpl();

    @Test
    public void search() {
        int search = binarySearch.search(new int[]{1, 2}, 0);
        Assert.assertEquals(-1, search);
        search = binarySearch.search(new int[]{1, 2}, 10);
        Assert.assertEquals(-1, search);
        search = binarySearch.search(new int[]{1, 2, 4, 7, 10}, 7);
        Assert.assertEquals(3, search);
    }
}