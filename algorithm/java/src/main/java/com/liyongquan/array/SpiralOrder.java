package com.liyongquan.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * <p>
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SpiralOrder {
    /**
     * 左右上下分别增加一行/列来简化边界处理
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length <= 0) {
            return Collections.emptyList();
        }
        int row = matrix.length, col = matrix[0].length;
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
        List<Integer> r = new ArrayList<>(row * col);
        int direct = 0;
        int i = 1, j = 1;
        while (visit[i][j] == 0) {
            visit[i][j] = 1;
            r.add(matrix[i - 1][j - 1]);
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

    /**
     * 解法2：一层层扫描
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        if (matrix.length <= 0) {
            return Collections.emptyList();
        }
        int row = matrix.length, col = matrix[0].length;
        List<Integer> res = new ArrayList<>(row * col);
        int x = 0, y = 0;
        int middleX = row % 2 == 0 ? row / 2 - 1 : row / 2;
        int middleY = col % 2 == 0 ? col / 2 - 1 : col / 2;
        while (x <= middleX && y <= middleY) {
            int width = col - x * 2, high = row - y * 2;
            if (width == 1) {
                for (int i = x; i < x + high; i++) {
                    res.add(matrix[i][y]);
                }
            } else if (high == 1) {
                for (int i = y; i < y + width; i++) {
                    res.add(matrix[x][i]);
                }
            } else {
                //上边
                for (int i = y; i < y + width - 1; i++) {
                    res.add(matrix[x][i]);
                }
                //右边
                for (int i = x; i < x + high - 1; i++) {
                    res.add(matrix[i][x + width - 1]);
                }
                //下边
                for (int i = y + width - 1; i > y; i--) {
                    res.add(matrix[x + high - 1][i]);
                }
                //左边
                for (int i = x + high - 1; i > x; i--) {
                    res.add(matrix[i][y]);
                }
            }
            x++;
            y++;
        }
        return res;
    }
}
