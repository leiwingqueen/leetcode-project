package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class PalindromePartitioningTest {

    @Test
    public void partition() {
        PalindromePartitioning pp = new PalindromePartitioning();
        List<List<String>> list = pp.partition2("aab");
        for (List<String> str : list) {
            String reduce = str.stream().reduce("", (a, b) -> a + "," + b);
            log.info(reduce);
        }
    }
}