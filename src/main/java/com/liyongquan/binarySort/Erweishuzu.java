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
        int i=0;
        int j=col-1;
        while (i<=row-1&&j>=0){
            if (matrix[i][j]==target) {
                return true;
            }else if(matrix[i][j]>target){
               j--;
            }else{
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Erweishuzu erweishuzu = new Erweishuzu();
        /*int[][] matrix = new int[][]{
                {1,2,3,4,5},
                {6,7,8,9,10},
                {11,12,13,14,15},
                {16,17,18,19,20},
                {21,22,23,24,25}};*/
        int[][] matrix=new int[][]{{-5}};
        /*int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };*/
        boolean numberIn2DArray = erweishuzu.findNumberIn2DArray(matrix, -5);
        System.out.println(numberIn2DArray);
    }
}
