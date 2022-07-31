package com.liyongquan.weeklycontest.wc304;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClosestMeetingNodeTest {
    private ClosestMeetingNode closestMeetingNode = new ClosestMeetingNode();

    @Test
    public void closestMeetingNode() {
        int res = closestMeetingNode.closestMeetingNode(new int[]{1, 2, -1}, 0, 2);
        Assert.assertEquals(2, res);
    }

    @Test
    public void test2() {
        int res = closestMeetingNode.closestMeetingNode(new int[]{4, 4, 8, -1, 9, 8, 4, 4, 1, 1}, 5, 6);
        Assert.assertEquals(1, res);
    }
}