package com.liyongquan.binarySort;

import com.liyongquan.hash.LeastBricks;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LeastBricksTest {
    private LeastBricks lb = new LeastBricks();

    @Test
    public void leastBricks() {
        List<List<Integer>> input = build(new int[][]{
                {1, 2, 2, 1}, {3, 1, 2}, {1, 3, 2}, {2, 4}, {3, 1, 2}, {1, 3, 1, 1}
        });
        int res = lb.leastBricks(input);
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }

    private List<List<Integer>> build(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>(matrix.length);
        for (int[] list : matrix) {
            List<Integer> l = new ArrayList<>(list.length);
            for (int num : list) {
                l.add(num);
            }
            res.add(l);
        }
        return res;
    }
}