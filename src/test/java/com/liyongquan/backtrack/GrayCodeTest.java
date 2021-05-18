package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class GrayCodeTest {
    private GrayCode gc = new GrayCode();

    @Test
    public void grayCode() {
        List<Integer> res = gc.grayCode(2);
        for (Integer re : res) {
            log.info("{}", re);
        }
    }
}