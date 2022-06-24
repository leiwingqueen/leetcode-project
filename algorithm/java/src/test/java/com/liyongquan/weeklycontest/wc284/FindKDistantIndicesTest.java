package com.liyongquan.weeklycontest.wc284;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class FindKDistantIndicesTest {
    private FindKDistantIndices indices = new FindKDistantIndices();

    @Test
    public void findKDistantIndices() {
        List<Integer> res = indices.findKDistantIndices(new int[]{3, 4, 9, 1, 3, 9, 5}, 9, 1);
        for (Integer r : res) {
            log.warn("{}", r);
        }
    }
}