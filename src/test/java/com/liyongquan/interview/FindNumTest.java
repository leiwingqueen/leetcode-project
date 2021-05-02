package com.liyongquan.interview;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class FindNumTest {
    private FindNum fn = new FindNum();

    @Test
    public void find() {
        List<Integer> res = fn.find(new int[]{9, 8, 7, 3, 4, 2, 1});
        for (Integer re : res) {
            log.info("{}", re);
        }
        Assert.assertArrayEquals(new Integer[]{9, 8, 7, 2, 1}, res.toArray(new Integer[]{}));
    }

    @Test
    public void test2() {
        List<Integer> res = fn.find(new int[]{2, 9, 8, 7, 3, 4, 2, 6, 1});
        for (Integer re : res) {
            log.info("{}", re);
        }
        Assert.assertArrayEquals(new Integer[]{1}, res.toArray(new Integer[]{}));
    }
}