package com.liyongquan.array;

import org.junit.Test;

import static org.junit.Assert.*;

public class Sum3NumTest {
    private Sum3Num sum3Num=new Sum3Num();
    @Test
    public void threeSumClosest2() {
        int i = sum3Num.threeSumClosest2(new int[]{0, 1, 2}, 3);
        System.out.println(i);
    }
}