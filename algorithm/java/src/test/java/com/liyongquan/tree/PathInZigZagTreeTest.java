package com.liyongquan.tree;

import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author liyongquan
 * @date 2021/7/29
 */
@Slf4j
public class PathInZigZagTreeTest {
    private PathInZigZagTree pt = new PathInZigZagTree();

    @Test
    public void pathInZigZagTree() {
        List<Integer> res = pt.pathInZigZagTree(14);
        for (Integer r : res) {
            log.info("{}", r);
        }
    }
}