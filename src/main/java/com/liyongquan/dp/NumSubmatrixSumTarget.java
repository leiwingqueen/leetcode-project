package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 1074. 元素和为目标值的子矩阵数量
 * <p>
 * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 * <p>
 * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 * <p>
 * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * 输出：4
 * 解释：四个只含 0 的 1x1 子矩阵。
 * 示例 2：
 * <p>
 * 输入：matrix = [[1,-1],[-1,1]], target = 0
 * 输出：5
 * 解释：两个 1x2 子矩阵，加上两个 2x1 子矩阵，再加上一个 2x2 子矩阵。
 * 示例 3：
 * <p>
 * 输入：matrix = [[904]], target = 0
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= matrix.length <= 100
 * 1 <= matrix[0].length <= 100
 * -1000 <= matrix[i] <= 1000
 * -10^8 <= target <= 10^8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class NumSubmatrixSumTarget {
    /**
     * 暴力+前缀和
     * <p>
     * 居然过了，不可思议
     * <p>
     * m行n列的矩阵，时间复杂度是O(m^2*n^2)
     *
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        //前缀和
        int[][] prefix = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                prefix[i][j] = matrix[i - 1][j - 1] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }
        //遍历
        int cnt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = i; k < row; k++) {
                    for (int l = j; l < col; l++) {
                        //计算[i,j]~[k,l]的和
                        int sum = prefix[k + 1][l + 1] - prefix[i][l + 1] - prefix[k + 1][j] + prefix[i][j];
                        //log.info("[{},{}]-[{},{}]:{}", i, j, k, l, sum);
                        if (sum == target) {
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }

    /**
     * 优化方案，减少一个维度的遍历
     *
     * @param matrix
     * @param target
     * @return
     */
    public int numSubmatrixSumTarget2(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        //前缀和
        int[][] prefix = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                prefix[i][j] = matrix[i - 1][j - 1] + prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1];
            }
        }
        //遍历
        int cnt = 0;
        for (int r1 = 0; r1 < row; r1++) {
            for (int r2 = r1; r2 < row; r2++) {
                Map<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                for (int j = 0; j < col; j++) {
                    //计算[r1,0]~[r2,j]的前缀和
                    int sum = prefix[r2 + 1][j + 1] - prefix[r1][j + 1];
                    //log.info("[{},{}]-[{},{}]:{}", i, j, k, l, sum);
                    cnt += map.getOrDefault(sum - target, 0);
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return cnt;
    }
}
