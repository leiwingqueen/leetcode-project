package com.liyongquan.weeklycontest.wc274;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaximumInvitationsTest {
    private MaximumInvitations mi = new MaximumInvitations();

    @Test
    public void maximumInvitations() {
        int res = mi.maximumInvitations2(new int[]{2, 2, 1, 2});
        Assert.assertEquals(3, res);
    }

    //[3,0,1,4,1]
    @Test
    public void test2() {
        int res = mi.maximumInvitations(new int[]{3, 0, 1, 4, 1});
        Assert.assertEquals(4, res);
    }
}