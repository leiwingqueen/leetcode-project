package com.liyongquan.binarySort;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FindClosestTest {
    private FindClosest fc = new FindClosest();

    //["I","am","a","student","from","a","university","in","a","city"]
    //"a"
    //"student"
    @Test
    public void findClosest() {
        boolean res = fc.check(new String[]{"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"}, "a", "student", 1);
        Assert.assertEquals(false, res);

        boolean r = fc.check(new String[]{"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"}, "a", "student", 2);
        Assert.assertEquals(true, r);
    }

    @Test
    public void test() {
        int r = fc.findClosest(new String[]{"I", "am", "a", "student", "from", "a", "university", "in", "a", "city"}, "a", "student");
        Assert.assertEquals(1, r);
    }
}