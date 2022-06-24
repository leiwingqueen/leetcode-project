package com.liyongquan.heap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

@Slf4j
public class RankTest {
    private Rank rank=new Rank();

    @Test
    public void rank() {
        int[] arr = {90, 1, 3, 4, 5, 91, 20, 92, 0, 93, 94, 95, 96, 97, 98, 99};
        int[] res = this.rank.rank(arr, 10);
        for (int re : res) {
            log.info("{}", re);
        }
    }
}