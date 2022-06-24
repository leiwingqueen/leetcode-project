package com.liyongquan.math;

import org.junit.Test;

public class HammingWeightTest {
    private HammingWeight hammingWeight=new HammingWeight();
    @Test
    public void hammingWeight(){
        int i = hammingWeight.hammingWeight(-1);
        System.out.println(i);
        System.out.println(-1&1);
    }
}