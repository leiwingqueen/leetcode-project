package com.liyongquan.dfs;

//688. 骑士在棋盘上的概率
//在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
//
//象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
//
//
//
//每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
//
//骑士继续移动，直到它走了 k 步或离开了棋盘。
//
//返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
//
// 
//
//示例 1：
//
//输入: n = 3, k = 2, row = 0, column = 0
//输出: 0.0625
//解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
//在每一个位置上，也有两种移动可以让骑士留在棋盘上。
//骑士留在棋盘上的总概率是0.0625。
//示例 2：
//
//输入: n = 1, k = 0, row = 0, column = 0
//输出: 1.00000
// 
//
//提示:
//
//1 <= n <= 25
//0 <= k <= 100
//0 <= row, column <= n
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KnightProbability {
    public static final int[][] DIRS = {
            {-1, -2},
            {-2, -1},
            {-2, 1},
            {-1, 2},
            {1, -2},
            {2, -1},
            {2, 1},
            {1, 2},
    };

    Map<Integer, Double> cache = new HashMap<>();

    /**
     * 记忆+dfs
     * <p>
     * 性能击败5%
     *
     * @param n
     * @param k
     * @param row
     * @param column
     * @return
     */
    public double knightProbability(int n, int k, int row, int column) {
        int key = row | (column << 7) | (k << 14);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (k == 0) {
            return 1D;
        }
        double sum = 0D;
        for (int[] dir : DIRS) {
            int x = row + dir[0];
            int y = column + dir[1];
            if (x >= 0 && x < n && y >= 0 && y < n) {
                sum += 0.125D * knightProbability(n, k - 1, x, y);
            }
        }
        cache.put(key, sum);
        return sum;
    }

    /**
     * dp解法
     * <p>
     * 这样算状态压缩么
     * <p>
     * 时间复杂度O(k*n^2)
     *
     * @param n
     * @param k
     * @param row
     * @param column
     * @return
     */
    public double knightProbability2(int n, int k, int row, int column) {
        //初始化
        Map<Integer, Double> mp = new HashMap<>();
        mp.put(row * n + column, 1D);
        //dp迭代
        for (int i = 0; i < k; i++) {
            Map<Integer, Double> mp2 = new HashMap<>();
            for (Map.Entry<Integer, Double> entry : mp.entrySet()) {
                int x = entry.getKey() / n;
                int y = entry.getKey() % n;
                for (int[] dir : DIRS) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        mp2.put(nx * n + ny, mp2.getOrDefault(nx * n + ny, 0D) + entry.getValue() * 0.125D);
                    }
                }
            }
            mp = mp2;
        }
        //统计和
        double sum = 0D;
        for (Map.Entry<Integer, Double> entry : mp.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }
}
