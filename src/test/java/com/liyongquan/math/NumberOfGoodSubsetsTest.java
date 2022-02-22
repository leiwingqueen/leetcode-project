package com.liyongquan.math;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class NumberOfGoodSubsetsTest {
    public static final int MX = 30;
    public static final List<Integer> PRIME = new ArrayList<>();

    private NumberOfGoodSubsets subsets = new NumberOfGoodSubsets();

    //提前计算好相应的质数
    static {
        for (int i = 2; i <= MX; i++) {
            boolean flag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                PRIME.add(i);
            }
        }
    }

    @Test
    public void numberOfGoodSubsets() {
        StringBuilder sb = new StringBuilder();
        for (Integer num : PRIME) {
            sb.append(num + ",");
        }
        System.out.println(sb);
    }

    @Test
    public void testNumberOfGoodSubsets() {
        int res = subsets.numberOfGoodSubsets(new int[]{1, 2, 3, 4});
        Assert.assertEquals(6, res);
    }
}