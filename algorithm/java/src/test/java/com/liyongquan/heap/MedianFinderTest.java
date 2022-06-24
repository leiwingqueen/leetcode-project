package com.liyongquan.heap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/8/27
 */
@Slf4j
public class MedianFinderTest {
    private MedianFinder mf = new MedianFinder();

    @Test
    public void addNum() {
        mf.addNum(1);
        mf.addNum(2);
        mf.addNum(3);
        double median = mf.findMedian();
        log.info("{}", median);
    }
}