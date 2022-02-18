package com.liyongquan.backtrack;

//你有一块棋盘，棋盘上有一些格子已经坏掉了。你还有无穷块大小为1 * 2的多米诺骨牌，你想把这些骨牌不重叠地覆盖在完好的格子上，请找出你最多能在棋盘上放多少块骨牌？这些骨牌可以横着或者竖着放。
//
// 
//
//输入：n, m代表棋盘的大小；broken是一个b * 2的二维数组，其中每个元素代表棋盘上每一个坏掉的格子的位置。
//
//输出：一个整数，代表最多能在棋盘上放的骨牌数。
//
// 
//
//示例 1：
//
//输入：n = 2, m = 3, broken = [[1, 0], [1, 1]]
//输出：2
//解释：我们最多可以放两块骨牌：[[0, 0], [0, 1]]以及[[0, 2], [1, 2]]。（见下图）
//
//
// 
//
//示例 2：
//
//输入：n = 3, m = 3, broken = []
//输出：4
//解释：下图是其中一种可行的摆放方式
//
//
// 
//
//限制：
//
//1 <= n <= 8
//1 <= m <= 8
//0 <= b <= n * m
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/broken-board-dominoes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.HashMap;
import java.util.Map;

public class Domino {
    private Map<Long, Integer> cache = new HashMap<>();

    public static final int[][] DIRS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1},
    };

    /**
     * 回溯解法
     * <p>
     * 超时
     *
     * @param n
     * @param m
     * @param broken
     * @return
     */
    public int domino(int n, int m, int[][] broken) {
        long state = 0;
        for (int[] b : broken) {
            int idx = b[0] * m + b[1];
            state |= 1 << idx;
        }
        return backtrace(state, n, m);
    }

    private int backtrace(long state, int n, int m) {
        if (cache.containsKey(state)) {
            return cache.get(state);
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int idx = i * m + j;
                if ((state & (1 << idx)) == 0) {
                    //尝试所有方向看能不能放得下
                    for (int[] dir : DIRS) {
                        int x = dir[0] + i;
                        int y = dir[1] + j;
                        if (x >= 0 && x < n && y >= 0 && y < m && (state & (1 << (x * m + y))) == 0) {
                            int ns = (1 << idx) | (1 << (x * m + y));
                            state |= ns;
                            max = Math.max(max, backtrace(state, n, m) + 1);
                            state ^= ns;
                        }
                    }
                }
            }
        }
        cache.put(state, max);
        return max;
    }
}