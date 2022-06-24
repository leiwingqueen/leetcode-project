package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/26
 */
@Slf4j
public class NumRescueBoatsTest {
    private NumRescueBoats boats = new NumRescueBoats();

    @Test
    public void numRescueBoats() {
        int res = boats.numRescueBoats2(new int[]{3, 2, 2, 1}, 3);
        log.info("{}", res);
    }
}