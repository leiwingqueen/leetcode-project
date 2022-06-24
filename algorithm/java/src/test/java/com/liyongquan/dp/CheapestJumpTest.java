package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

/**
 * @author liyongquan
 * @date 2021/11/6
 */
@Slf4j
public class CheapestJumpTest {
    private CheapestJump jump = new CheapestJump();

    @Test
    public void cheapestJump() {
        List<Integer> list = jump.cheapestJump(new int[]{0, 0, 0, 0, 0, 0}, 3);
        for (Integer l : list) {
            log.info("{}", l);
        }
    }
}