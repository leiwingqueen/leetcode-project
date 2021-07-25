package com.liyongquan.hash;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/7/25
 */
@Slf4j
public class RestoreArrayTest {
    private RestoreArray ra = new RestoreArray();

    @Test
    public void restoreArray() {
        int[][] pairs = {
                {2, 1}, {3, 4}, {3, 2}
        };
        int[] res = ra.restoreArray2(pairs);
        for (int re : res) {
            log.info("{}", re);
        }
    }
}