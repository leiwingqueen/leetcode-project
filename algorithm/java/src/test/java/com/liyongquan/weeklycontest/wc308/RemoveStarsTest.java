package com.liyongquan.weeklycontest.wc308;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemoveStarsTest {
    private RemoveStars removeStars = new RemoveStars();

    @Test
    public void removeStars() {
        String res = removeStars.removeStars("leet**cod*e");
        Assert.assertEquals("lecoe", res);
    }
}