package com.liyongquan.twopointer;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinimumOperationsTest {

    @Test
    public void minimumOperations() {
        MinimumOperations mp = new MinimumOperations();
        int result = mp.minimumOperations2("rrryyyrryyyrr");
        System.out.println(result);
        Assert.assertEquals(2, result);
        int result2 = mp.minimumOperations2("yyyyryyyy");
        Assert.assertEquals(3, result2);
    }
}