package com.liyongquan.tesla;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class T1Test {

    @Test
    public void solution() {
        int res = T1.solution(10, 21);
        Assert.assertEquals(7, res);
    }
}