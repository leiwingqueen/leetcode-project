package com.liyongquan.backtrack;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liyongquan
 * @date 2021/8/15
 */
@Slf4j
public class OutOfBoundaryPaths {
    public static final int[][] DIRS = {
            {-1, 0},
            {1, 0},
            {0, 1},
            {0, -1},
    };

    public static final int MOD = 1000000007;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove == 0) {
            return 0;
        }
        int res = 0;
        for (int[] dir : DIRS) {
            int x = startRow + dir[0], y = startColumn + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n) {
                log.info("add [{},{}],move:{}", x, y, maxMove);
                res = (res + 1) % MOD;
            } else {
                res = (res + findPaths(m, n, maxMove - 1, x, y)) % MOD;
            }
        }
        return res;
    }
}
