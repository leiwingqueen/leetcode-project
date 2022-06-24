package com.liyongquan.tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class FindMinHeightTreesTest {
    private FindMinHeightTrees ht = new FindMinHeightTrees();

    //4
    //[[1,0],[1,2],[1,3]]
    @Test
    public void findMinHeightTrees() {
        List<Integer> res = ht.findMinHeightTrees(4, new int[][]{
                {1, 0},
                {1, 2},
                {1, 3}
        });
        for (Integer r : res) {
            log.info("{}", r);
        }
    }
}