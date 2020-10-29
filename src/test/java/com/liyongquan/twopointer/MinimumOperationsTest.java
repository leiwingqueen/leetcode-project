package com.liyongquan.twopointer;

import org.junit.Test;

import static org.junit.Assert.*;

public class MinimumOperationsTest {

    @Test
    public void minimumOperations() {
        MinimumOperations mp = new MinimumOperations();
        int result = mp.minimumOperations("rrryyyrryyyrr");
        System.out.println(result);
    }
}