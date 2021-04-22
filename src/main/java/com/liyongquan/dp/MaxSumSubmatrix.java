package com.liyongquan.dp;

//给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
//
// 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,0,1],[0,-2,3]], k = 2
//输出：2
//解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
//
//
// 示例 2：
//
//
//输入：matrix = [[2,2,-1]], k = 3
//输出：3
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 100
// -100 <= matrix[i][j] <= 100
// -105 <= k <= 105
//
//
//
//
// 进阶：如果行数远大于列数，该如何设计解决方案？
// Related Topics 队列 二分查找 动态规划
// 👍 208 👎 0


import java.util.TreeSet;

public class MaxSumSubmatrix {
    /**
     * 先尝试暴力解法
     * <p>
     * 时间复杂度O((m*n)^3)
     * <p>
     * 超时
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //计算[i,j]为起点的最大和
                for (int l = i; l < row; l++) {
                    for (int m = j; m < col; m++) {
                        //统计[i,j],[l,m]之间的和
                        int sum = 0;
                        for (int n = i; n <= l; n++) {
                            for (int o = j; o <= m; o++) {
                                sum += matrix[n][o];
                            }
                        }
                        if (sum <= k) {
                            max = Math.max(sum, max);
                        }
                    }
                }
            }
        }
        return max;
    }

    /**
     * 前缀和进行优化
     * <p>
     * f(i,j)为左上角为[0,0]，右下角为[i-1][j-1]
     * <p>
     * 时间复杂度O((m*n)^2)
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix2(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        //前缀和
        int[][] preSum = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //计算[i,j]为起点的最大和
                for (int l = i; l < row; l++) {
                    for (int m = j; m < col; m++) {
                        //统计[i,j],[l,m]之间的和
                        int sum = preSum[l + 1][m + 1] - preSum[i][m + 1] - preSum[l + 1][j] + preSum[i][j];
                        if (sum <= k) {
                            max = Math.max(sum, max);
                        }
                    }
                }
            }
        }
        return max;
    }

    /**
     * 二维降成一维
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix3(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        //枚举上下边界
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = i; j < row; j++) {
                //s[i]表是前i列的汇总的和
                int[] s = new int[col + 1];
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for (int l = 1; l <= col; l++) {
                    s[l] = s[l - 1];
                    for (int m = i; m <= j; m++) {
                        s[l] += matrix[m][l - 1];
                    }
                    //关键,找到s[l]-k的上边界，即为s[a]>=s[l]-k，且a<l
                    Integer ceiling = set.ceiling(s[l] - k);
                    if (ceiling != null) {
                        max = Math.max(max, s[l] - ceiling);
                    }
                    set.add(s[l]);
                }
            }
        }
        return max;
    }

    /**
     * 在上面的基础上再用前缀和优化一版
     * <p>
     * 时间复杂度O(m*m*n*log(n))
     *
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix4(int[][] matrix, int k) {
        int row = matrix.length, col = matrix[0].length;
        //preSum[i][j]为上边界为0,下边界为i-1的第j列的和
        int[][] preSum = new int[row + 1][col + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        //枚举上下边界
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = i; j < row; j++) {
                //s[i]表是前i列的汇总的和
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for (int l = 0; l < col; l++) {
                    //[i,0]~[j,l]
                    int sl = preSum[j + 1][l + 1] - preSum[i][l + 1];
                    //关键,找到s[l]-k的上边界，即为s[a]>=s[l]-k，且a<l
                    Integer ceiling = set.ceiling(sl - k);
                    if (ceiling != null) {
                        max = Math.max(max, sl - ceiling);
                    }
                    set.add(sl);
                }
            }
        }
        return max;
    }
}
