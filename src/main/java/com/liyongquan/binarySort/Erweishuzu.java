package com.liyongquan.binarySort;

public class Erweishuzu {
    /**
     * 找到中轴线中>=target的最小数字，然后在该中轴线的当前列的上面和当前行的左边进行查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int row=matrix.length;
        int col=row>0?matrix[0].length:0;
        if (row<=0||col<=0) {
            return false;
        }
        int[] zhongzhou = findZhongzhou(matrix, target, row, col);
        if (zhongzhou==null) {
            return false;
        }
        System.out.println(String.format("x:%s,y:%s",zhongzhou[0],zhongzhou[1]));
        return find(matrix, zhongzhou[0], zhongzhou[1], target);
    }

    /**
     * 由于矩阵不是一个严格的n*n的矩阵，如果点已经移动到最右/最下方，则剩下的点的移动只需要平移
     *
     * 这里还有个优化点，是否可以考虑使用二分查找
     * @param matrix
     * @param target
     * @param row
     * @param col
     * @return
     */
    private int[] findZhongzhou(int[][] matrix,int target,int row,int col){
        int i,j;
        i=j=0;
        while (i<row||j<col){
            if (i>=row) {
                i=row-1;
            }
            if (j>=col) {
                j=col-1;
            }
            if (matrix[i][j]>=target) {
                return new int[]{i,j};
            }
            i++;
            j++;
        }
        return null;
    }


    /**
     * 二分查找?
     * @param matrix
     * @param x
     * @param y
     * @param target
     * @return
     */
    private boolean find(int[][] matrix,int x,int y,int target){
        for (int i = 0; i < x; i++) {
            if (matrix[i][y]==target) {
                return true;
            }
        }
        for (int i = 0; i <= y; i++) {
            if (matrix[x][i]==target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Erweishuzu erweishuzu = new Erweishuzu();
        int[][] matrix = new int[][]{
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}};
        /*int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };*/
        boolean numberIn2DArray = erweishuzu.findNumberIn2DArray(matrix, 15);
        System.out.println(numberIn2DArray);
    }
}
