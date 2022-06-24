package com.liyongquan.dp;

public class SumPath {
    public int minPathSum(int[][] grid) {
        int row= grid.length;
        int col=grid[0].length;
        int[][] result=new int[row][col];
        int rowSum=0;
        for (int i = 0; i < row; i++) {
            rowSum+=grid[i][0];
            result[i][0]=rowSum;
        }
        int colSum=0;
        for (int i = 0; i < col; i++) {
            colSum+=grid[0][i];
            result[0][i]=colSum;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                result[i][j]=min(result[i-1][j],result[i][j-1])+grid[i][j];
            }
        }
        return result[row-1][col-1];
    }

    private int min(int a,int b){
        return a<b?a:b;
    }

    public static void main(String[] args) {
        SumPath sumPath=new SumPath();
        int[][] grid=new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        int i = sumPath.minPathSum(grid);
        System.out.println(i);
    }
}
