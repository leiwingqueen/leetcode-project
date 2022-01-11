package com.liyongquan.twopointer;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class FindRLEArrayTest {
    private FindRLEArray array = new FindRLEArray();

    //[[1,3],[2,1],[3,2]]
    //[[2,3],[3,3]]
    @Test
    public void findRLEArray() {
        int[][] encode1 = {
                {1, 3}, {2, 1}, {3, 2}
        };
        int[][] encode2 = {
                {2, 3}, {3, 3}
        };
        List<List<Integer>> res = array.findRLEArray(encode1, encode2);
        for (List<Integer> re : res) {
            log.info("[{},{}]", re.get(0), re.get(1));
        }
    }
}