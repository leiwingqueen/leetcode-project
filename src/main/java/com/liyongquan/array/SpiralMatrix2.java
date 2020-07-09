package com.liyongquan.array;

/**
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SpiralMatrix2 {
    /**
     * 左右上下分别增加一行/列来简化边界处理
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            return new int[][]{};
        }
        int row = n, col = n;
        int[][] visit = new int[row + 2][col + 2];
        //边界设置为已访问
        for (int i = 0; i < col + 2; i++) {
            visit[0][i] = 1;
            visit[row + 1][i] = 1;
        }
        for (int i = 0; i < row + 2; i++) {
            visit[i][0] = 1;
            visit[i][col + 1] = 1;
        }
        //0,1,2,3 分别代表 右,下，左，上
        int[][] r = new int[row][col];
        int direct = 0;
        int i = 1, j = 1;
        int num = 1;
        while (visit[i][j] == 0) {
            visit[i][j] = 1;
            r[i - 1][j - 1] = num;
            num++;
            int[] move = move(i, j, direct);
            //走到尽头，换一个方向
            if (visit[move[0]][move[1]] == 1) {
                direct = (direct + 1) % 4;
                int[] move2 = move(i, j, direct);
                i = move2[0];
                j = move2[1];
            } else {
                i = move[0];
                j = move[1];
            }
        }
        return r;
    }

    private int[] move(int i, int j, int direct) {
        if (direct == 0) {
            j++;
        } else if (direct == 1) {
            i++;
        } else if (direct == 2) {
            j--;
        } else {
            i--;
        }
        return new int[]{i, j};
    }
}
