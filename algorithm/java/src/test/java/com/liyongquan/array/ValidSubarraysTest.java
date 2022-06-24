package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ValidSubarraysTest {
    private ValidSubarrays vs = new ValidSubarrays();

    @Test
    public void validSubarray3() {
        int res = vs.validSubarray3(new int[]{1, 4, 2, 5, 3});
        Assert.assertEquals(11, res);
    }
}