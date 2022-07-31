package com.liyongquan.weeklycontest.wc304;

import com.liyongquan.weeklycontest.wc302.MaximumSum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaximumGroupsTest {
    private MaximumGroups groups = new MaximumGroups();

    @Test
    public void maximumGroups() {
        int res = groups.maximumGroups(new int[]{10, 6, 12, 7, 3, 5});
        Assert.assertEquals(3, res);
    }
}