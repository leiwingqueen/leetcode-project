package com.liyongquan.weeklycontest.bwc69;

//给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。
//
//给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
//
//覆盖所有 空 格子。
//不覆盖任何 被占据 的格子。
//我们可以放入任意数目的邮票。
//邮票可以相互有 重叠 部分。
//邮票不允许 旋转 。
//邮票必须完全在矩阵 内 。
//如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。
//
// 
//
//示例 1：
//
//
//
//输入：grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
//输出：true
//解释：我们放入两个有重叠部分的邮票（图中标号为 1 和 2），它们能覆盖所有与空格子。
//示例 2：
//
//
//
//输入：grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
//输出：false
//解释：没办法放入邮票覆盖所有的空格子，且邮票不超出网格图以外。
// 
//
//提示：
//
//m == grid.length
//n == grid[r].length
//1 <= m, n <= 105
//1 <= m * n <= 2 * 105
//grid[r][c] 要么是 0 ，要么是 1 。
//1 <= stampHeight, stampWidth <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/stamping-the-grid
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class PossibleToStamp {
    private int m;
    private int n;
    private boolean[][] visit;
    private int[][] grid;

    /**
     * 暴力
     * <p>
     * 时间复杂度O(n^6)
     *
     * @param grid
     * @param stampHeight
     * @param stampWidth
     * @return
     */
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        this.visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !visit[i][j]) {
                    if (!check(i, j, stampHeight, stampWidth)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean check(int x, int y, int height, int width) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //行的范围[x-i,x-i+height-1]
                //列的范围[y-j,y-j+width-1]
                if (check(x, y, height, width, i, j)) {
                    for (int k = x - i; k < x - i + height; k++) {
                        for (int l = y - j; l < y - j + width; l++) {
                            visit[k][j] = true;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean check(int x, int y, int height, int width, int i, int j) {
        //行的范围[x-i,x-i+height-1]
        //列的范围[y-j,y-j+width-1]
        for (int k = x - i; k < x - i + height; k++) {
            if (k < 0 || k >= m) {
                return false;
            }
            for (int l = y - j; l < y - j + width; l++) {
                if (l < 0 || l >= n || grid[k][l] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    //二维差分数组
    public boolean possibleToStamp2(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length, n = grid[0].length;
        //维护前缀和，方便判断某个格子能否贴
        int[][] gridSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                gridSum[i][j] = grid[i - 1][j - 1] + gridSum[i - 1][j] + gridSum[i][j - 1] - gridSum[i - 1][j - 1];
            }
        }
        //维护一个差分数组，方便后面统计是否贴上
        int[][] diff = new int[m][n];
        //判断能否放入
        for (int i = 0; i < m - stampHeight; i++) {
            for (int j = 0; j < n - stampWidth; j++) {
                //[i,j]-[i+height-1,j+width-1]
                int s = gridSum[i + stampHeight][j + stampWidth] - gridSum[i + stampHeight][j] - gridSum[i][j + stampWidth] + gridSum[i][j];
                //能贴，尽量贴
                if (s == 0) {
                    //差分数组更新
                    diff[i][j]++;
                    diff[i][j + stampWidth]--;
                    diff[i + stampHeight][j]--;
                    diff[i + stampHeight][j + stampWidth]++;
                }
            }
        }
        return true;
    }
}
