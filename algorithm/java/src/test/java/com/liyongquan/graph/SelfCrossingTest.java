package com.liyongquan.graph;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/10/29
 */
@Slf4j
public class SelfCrossingTest {
    private SelfCrossing crossing = new SelfCrossing();

    @Test
    public void isSelfCrossing() {
        boolean res = crossing.isSelfCrossing(new int[]{1, 2, 3, 4});
        Assert.assertEquals(false, res);
    }
}