package com.liyongquan.weeklycontest.wc234;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NumDifferentIntegersTest {
    private NumDifferentIntegers numDifferent = new NumDifferentIntegers();

    @Test
    public void numDifferentIntegers() {
        int res = numDifferent.numDifferentIntegers("a123bc34d8ef34");
        Assert.assertEquals(3, res);
    }
}