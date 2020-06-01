package com.liyongquan.stack;

import org.junit.Test;

import static org.junit.Assert.*;

public class Kuohao2Test {
    private Kuohao2 kuohao2=new Kuohao2();
    @Test
    public void isValid() {
        boolean valid = kuohao2.isValid("()[]{}");
        assertEquals(true,valid);
    }
}