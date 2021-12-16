package com.liyongquan.binarySort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/12/16
 */
@Slf4j
public class SmallestDistancePairTest {
    private SmallestDistancePair pair = new SmallestDistancePair();

    //[1,3,1]
    //1
    @Test
    public void smallestDistancePair() {
        int res = pair.smallestDistancePair(new int[]{1, 3, 1}, 1);
        Assert.assertEquals(0, res);
    }

    //[38,33,57,65,13,2,86,75,4,56]
    //26
    @Test
    public void test2() {
        int[] arr = {38, 33, 57, 65, 13, 2, 86, 75, 4, 56};
        Arrays.sort(arr);
        int[] smaller = pair.findSmaller(arr, 84);
        log.warn("{}", smaller);
        int res = pair.smallestDistancePair(arr, 26);
        Assert.assertEquals(36, res);
    }

    //[10,6,2,10,5,4,0,1,5,2,5,5,5,0,4,9,8,6,7,9,1,10,4,8,6,3,6,2,1,7,5,0,2,6,10,10,0,3,9,0,8,3,5,9,4,4,5,2,2,7]
    //444
    @Test
    public void test3() {
        int[] arr = {
                10, 6, 2, 10, 5, 4, 0, 1, 5, 2, 5, 5, 5, 0, 4,
                9, 8, 6, 7, 9, 1, 10, 4, 8, 6, 3, 6, 2, 1, 7,
                5, 0, 2, 6, 10, 10, 0, 3, 9, 0, 8, 3, 5, 9, 4, 4, 5, 2, 2, 7};
        int res = pair.smallestDistancePair(arr, 444);
        Assert.assertEquals(2, res);
        Arrays.sort(arr);
        int[] smaller = pair.findSmaller(arr, 2);
        log.info("{}", smaller);
    }
}