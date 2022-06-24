package com.liyongquan.prefixSum;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ContiguousArrayTest {
    private ContiguousArray ca = new ContiguousArray();

    @Test
    public void findMaxLength() {
        int res = ca.findMaxLength(new int[]{0, 0, 1, 0, 0, 0, 1, 1});
        log.info("{}", res);
        Assert.assertEquals(6, res);
    }
}