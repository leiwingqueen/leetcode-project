package com.liyongquan.array;

/**
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * <p>
 * 输出:  [1,2,4,7,5,3,6,8,9]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diagonal-traverse
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindDiagonalOrder {
    //定义移动方向
    private int[][] direct = new int[][]{
            {-1, 1},
            {1, -1}
    };

    /**
     * 傻瓜解法
     *
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length <= 0) {
            return new int[0];
        }
        int row = matrix.length, col = matrix[0].length;
        int[] result = new int[row * col];
        //移动方向，0斜上，1-斜下
        int d = 0;
        int x = 0, y = 0;
        for (int i = 0; i < row * col; i++) {
            //System.out.println("x:" + x + ",y:" + y);
            result[i] = matrix[x][y];
            //计算下一个移动位置
            x += direct[d][0];
            y += direct[d][1];
            if (d == 0) {
                if (y >= col) {
                    x += 2;
                    y--;
                    d = 1;
                } else if (x < 0) {
                    x++;
                    d = 1;
                }
            } else {
                if (x >= row) {
                    y += 2;
                    x--;
                    d = 0;
                } else if (y < 0) {
                    y++;
                    d = 0;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindDiagonalOrder fd = new FindDiagonalOrder();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] diagonalOrder = fd.findDiagonalOrder(matrix);
        for (int i = 0; i < diagonalOrder.length; i++) {
            System.out.println(diagonalOrder[i]);
        }
    }
}
