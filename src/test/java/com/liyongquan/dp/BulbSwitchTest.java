package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/11/15
 */
@Slf4j
public class BulbSwitchTest {
    private BulbSwitch bs = new BulbSwitch();

    @Test
    public void bulbSwitch() {
        int r = bs.bulbSwitch(2);
        Assert.assertEquals(1, r);
        r = bs.bulbSwitch(4);
        Assert.assertEquals(2, r);
    }
}