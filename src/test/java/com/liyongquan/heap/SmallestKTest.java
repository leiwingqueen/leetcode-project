package com.liyongquan.heap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SmallestKTest {
    private SmallestK smallestK = new SmallestK();

    @Test
    public void smallestK2() {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        int[] res = smallestK.smallestK3(arr, 4);
        for (int num : res) {
            log.info("{}", num);
        }
    }
}