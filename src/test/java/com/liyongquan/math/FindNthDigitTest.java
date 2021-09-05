package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/9/5
 */
@Slf4j
public class FindNthDigitTest {
    private FindNthDigit digit = new FindNthDigit();

    /**
     * 先生成各个字符长度的前缀和
     */
    @Test
    public void testInit1() {
        List<Integer> list = new ArrayList<>();
        long pre = 0;
        long max = 10;
        int len = 1;
        while (true) {
            long total = (max - pre) * len;
            if (total > Integer.MAX_VALUE) {
                break;
            }
            list.add((int) total);
            pre = max;
            max = max * 10;
            len++;
        }
        //前缀和
        int[] prefix = new int[list.size() + 1];
        for (int i = 0; i < list.size(); i++) {
            prefix[i + 1] = prefix[i] + list.get(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int p : prefix) {
            sb.append(p + ",");
        }
        log.info("{}", sb.toString());
    }

    @Test
    public void findNthDigit() {
        int res = digit.findNthDigit(3);
        Assert.assertEquals(3, res);
        res = digit.findNthDigit(9);
        Assert.assertEquals(9, res);
        res = digit.findNthDigit(10);
        Assert.assertEquals(1, res);
        res = digit.findNthDigit(11);
        Assert.assertEquals(0, res);
    }

    @Test
    public void test() {
        int res = digit.findNthDigit(13);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test1() {
        int res = digit.findNthDigit(10);
        Assert.assertEquals(1, res);
    }

    @Test
    public void test2() {
        int res = digit.findNthDigit(2147483647);
    }
}