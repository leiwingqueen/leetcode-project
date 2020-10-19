package com.liyongquan.array;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BianryNumberToStringTest {
    private BianryNumberToString bn = new BianryNumberToString();

    @Test
    public void printBin() {
        String s = bn.printBin(0.625D);
        Assert.assertEquals("0.101", s);
        String s1 = bn.printBin(0.1D);
        Assert.assertEquals("ERROR", s1);
    }
}