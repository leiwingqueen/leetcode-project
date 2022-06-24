package com.liyongquan.weeklycontest.wc234;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReinitializePermutationTest {
    private ReinitializePermutation rp = new ReinitializePermutation();

    @Test
    public void test() {
        int res = rp.reinitializePermutation(4);
        System.out.println(res);
    }
}