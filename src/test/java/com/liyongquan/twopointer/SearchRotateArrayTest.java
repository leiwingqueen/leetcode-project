package com.liyongquan.twopointer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SearchRotateArrayTest {
    private SearchRotateArray sr = new SearchRotateArray();

    @Test
    public void search() {
        int search = sr.search(new int[]{1, 1, 1, 1, 1, 2, 1, 1, 1}, 2);
        System.out.println(search);
        assertEquals(5, search);
    }

    /**
     * [21, 21, -21, -20, -17, -8, -6, -2, -2, -1, 0, 2, 3, 4, 4, 6, 11, 13, 14, 16, 17, 18, 20]
     * 4
     */
    @Test
    public void search2() {
        int search = sr.search(new int[]{21, 21, -21, -20, -17, -8, -6, -2, -2, -1, 0, 2, 3, 4, 4, 6, 11, 13, 14, 16, 17, 18, 20}, 4);
        System.out.println(search);
        assertEquals(13, search);
    }

    /**
     * [1, -2]
     * -2
     */
    @Test
    public void search3() {
        int search = sr.search(new int[]{1, -2}, -2);
        System.out.println(search);
        assertEquals(1, search);
    }
}