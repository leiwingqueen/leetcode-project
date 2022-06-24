package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class NumFriendRequestsTest {
    private NumFriendRequests requests = new NumFriendRequests();

    @Test
    public void numFriendRequests2() {
        int r = requests.numFriendRequests2(new int[]{20, 30, 100, 110, 120});
        Assert.assertEquals(3, r);
    }
}