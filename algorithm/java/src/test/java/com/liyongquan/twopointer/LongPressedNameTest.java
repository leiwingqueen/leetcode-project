package com.liyongquan.twopointer;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongPressedNameTest {
    private LongPressedName lp = new LongPressedName();

    @Test
    public void isLongPressedName() {
        boolean r = lp.isLongPressedName("vtkgn", "vttkgnn");
        Assert.assertEquals(true, r);
    }
}