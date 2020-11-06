package com.liyongquan.twopointer;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchRotateArrayTest {
    private SearchRotateArray sr = new SearchRotateArray();

    @Test
    public void search() {
        int search = sr.search(new int[]{1, 1, 1, 1, 1, 2, 1, 1, 1}, 2);
        System.out.println(search);
        Assert.assertEquals(5, search);
    }
}