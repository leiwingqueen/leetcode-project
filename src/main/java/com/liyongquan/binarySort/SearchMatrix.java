package com.liyongquan.binarySort;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -109 <= matix[i][j] <= 109
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -109 <= target <= 109
 * 通过次数103,308提交次数235,078
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SearchMatrix {
    /**
     * 每个对角线上的 纵列 和 行列 都是有序的，我们可以以对角线为维度来进行二分查找
     * <p>
     * 问题：其实这种方式跟我们对每一行进行二分查找有什么区别
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < Math.max(row, col); i++) {
            //对角线的位置为(i,i),对应行的范围为(i,0)~(i,i)，对应列的范围为(0,i)~(i,i)
            //对行进行二分查找
            if (i < row) {
                int l = 0, r = i;
                while (l < r) {
                    int middle = (l + r) / 2;
                    if (matrix[i][middle] == target) {
                        return true;
                    } else if (matrix[i][middle] > target) {
                        r = middle - 1;
                    } else {
                        l = middle + 1;
                    }
                }
                if (matrix[i][l] == target) {
                    return true;
                }
            }
            //对纵列进行二分查找
            if (i < col) {
                int l = 0, r = i;
                while (l < r) {
                    int middle = (l + r) / 2;
                    if (matrix[middle][i] == target) {
                        return true;
                    } else if (matrix[middle][i] > target) {
                        r = middle - 1;
                    } else {
                        l = middle + 1;
                    }
                }
                if (matrix[l][i] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
