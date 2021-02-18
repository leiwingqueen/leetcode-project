package com.liyongquan.binarySort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class SearchMatrixTest {
    private SearchMatrix sm = new SearchMatrix();

    @Test
    public void searchMatrix() {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        boolean res = sm.searchMatrix(matrix, 5);
        log.info("{}", res);
        Assert.assertEquals(true, res);
    }
}