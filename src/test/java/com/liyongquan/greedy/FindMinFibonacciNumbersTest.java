package com.liyongquan.greedy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class FindMinFibonacciNumbersTest {
    private FindMinFibonacciNumbers fn = new FindMinFibonacciNumbers();

    @Test
    public void test1() {
        int max = 1_000_000_000;
        int f1 = 1, f2 = 1;
        List<Integer> list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        while (f2 < max) {
            int f3 = f1 + f2;
            list.add(f3);
            f1 = f2;
            f2 = f3;
        }
        StringBuilder sb = new StringBuilder();
        for (Integer num : list) {
            sb.append(num + ",");
        }
        log.info(sb.toString());
    }

    @Test
    public void findMinFibonacciNumbers() {
        int res = fn.findMinFibonacciNumbers2(7);
        Assert.assertEquals(2, res);
    }
}