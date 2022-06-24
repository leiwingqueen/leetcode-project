package com.liyongquan.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Deque;

import static org.junit.Assert.*;

@Slf4j
public class DecodeStringTest {
    private DecodeString ds = new DecodeString();

    @Test
    public void decodeString() {
        String res = ds.decodeString("3[a]2[bc]");
        log.info("{}", res);
    }
}