package com.liyongquan.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MaxStackTest {
    @Test
    public void test() {
        MaxStack ms = new MaxStack();
        ms.push(5);
        ms.push(1);
        int max = ms.popMax();
        log.info("{}", max);
        int res = ms.peekMax();
        log.info("{}",res);
    }
}