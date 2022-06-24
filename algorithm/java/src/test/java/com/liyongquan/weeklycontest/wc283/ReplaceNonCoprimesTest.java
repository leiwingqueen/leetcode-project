package com.liyongquan.weeklycontest.wc283;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class ReplaceNonCoprimesTest {
    private ReplaceNonCoprimes rnc = new ReplaceNonCoprimes();

    @Test
    public void replaceNonCoprimes() {
        List<Integer> res = rnc.replaceNonCoprimes(new int[]{287, 41, 49, 287, 899, 23, 23, 20677, 5, 825});
        for (Integer r : res) {
            log.info("{}", r);
        }
    }
}