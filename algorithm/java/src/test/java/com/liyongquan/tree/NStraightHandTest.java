package com.liyongquan.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NStraightHandTest {
    private NStraightHand hand = new NStraightHand();

    @Test
    public void isNStraightHand() {
        boolean res = hand.isNStraightHand(new int[]{1, 2, 3, 4, 5}, 4);
        Assert.assertEquals(false, res);
    }
}