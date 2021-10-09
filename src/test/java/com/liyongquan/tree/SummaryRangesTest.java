package com.liyongquan.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/10/9
 */
@Slf4j
public class SummaryRangesTest {

    @Test
    public void addNum() {
        SummaryRanges summaryRanges = new SummaryRanges();
        summaryRanges.addNum(1);
        summaryRanges.addNum(3);
        summaryRanges.addNum(7);
        summaryRanges.addNum(2);
        int[][] intervals = summaryRanges.getIntervals();
        for (int[] v : intervals) {
            log.info("[{},{}]", v[0], v[1]);
        }
    }
}