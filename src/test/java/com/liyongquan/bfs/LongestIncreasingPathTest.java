package com.liyongquan.bfs;

import com.liyongquan.array.LargeGroupPositions;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LongestIncreasingPathTest {
    LongestIncreasingPath lip = new LongestIncreasingPath();

    @Test
    public void longestIncreasingPath() {
        int[][] matrix = {
                {9, 9, 4}, {6, 6, 8}, {2, 1, 1}
        };
        int res = lip.longestIncreasingPath(matrix);
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}