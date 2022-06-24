package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class SpiralOrderTest {
    private SpiralOrder so = new SpiralOrder();

    @Test
    public void spiralOrder2() {
        List<Integer> res = so.spiralOrder2(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
        StringBuilder sb = new StringBuilder();
        for (Integer re : res) {
            sb.append(re + ",");
        }
        log.info("{}", sb.toString());
    }

    @Test
    public void test2() {
        List<Integer> res = so.spiralOrder2(new int[][]{
                {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
        });
    }
}