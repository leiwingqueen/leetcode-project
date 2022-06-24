package com.liyongquan.math;

import com.liyongquan.array.LargeGroupPositions;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/10/30
 */
@Slf4j
public class SingleNumberIIITest {
    private SingleNumberIII sn = new SingleNumberIII();

    @Test
    public void singleNumber() {
        int[] res = sn.singleNumber3(new int[]{1, 2, 1, 3, 2, 5});
        for (int re : res) {
            log.info("{}", re);
        }
    }
}