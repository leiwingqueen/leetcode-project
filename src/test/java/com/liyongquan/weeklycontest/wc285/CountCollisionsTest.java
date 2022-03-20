package com.liyongquan.weeklycontest.wc285;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class CountCollisionsTest {
    private CountCollisions collisions = new CountCollisions();

    @Test
    public void countCollisions() {
        int res = collisions.countCollisions("RLRSLL");
        log.info("{}", res);
    }
}