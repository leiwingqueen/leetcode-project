package com.liyongquan.slidewindow;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class MedianSlidingWindowTest {
    private MedianSlidingWindow ms = new MedianSlidingWindow();

    /**
     * [1,3,-1,-3,5,3,6,7]
     * 3
     */
    @Test
    public void medianSlidingWindow() {
        double[] res = ms.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        for (double re : res) {
            log.info("{}", re);
        }
    }

    /**
     * [1,4,2,3]
     * 4
     */
    @Test
    public void medianSlidingWindow2() {
        double[] res = ms.medianSlidingWindow(new int[]{1, 4, 2, 3}, 4);
        for (double re : res) {
            log.info("{}", re);
        }
    }
}