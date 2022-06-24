package com.liyongquan.heap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/9/8
 */
@Slf4j
public class FindMaximizedCapitalTest {
    private FindMaximizedCapital capital = new FindMaximizedCapital();

    /**
     * 2
     * 0
     * [1,2,3]
     * [0,1,1]
     */
    @Test
    public void findMaximizedCapital() {
        int res = capital.findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1});
        Assert.assertEquals(4, res);
    }
}