package com.liyongquan.hash;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/11/17
 */
@Slf4j
public class PerfectRectangleTest {
    private PerfectRectangle pr = new PerfectRectangle();

    @Test
    public void isRectangleCover() {
        int[][] args = {
                {0, 0, 4, 1}, {7, 0, 8, 2}, {6, 2, 8, 3},
                {5, 1, 6, 3}, {4, 0, 5, 1}, {6, 0, 7, 2},
                {4, 2, 5, 3}, {2, 1, 4, 3}, {0, 1, 2, 2},
                {0, 2, 2, 3}, {4, 1, 5, 2}, {5, 0, 6, 1}
        };
        boolean r = pr.isRectangleCover(args);
        Assert.assertEquals(true, r);
    }
}