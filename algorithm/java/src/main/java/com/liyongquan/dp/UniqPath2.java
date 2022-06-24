package com.liyongquan.dp;

public class UniqPath2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] paths=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                paths[i][j]=-1;
            }
        }
        return degrace(obstacleGrid,m,n,0,0,paths);
    }

    public int degrace(int[][] obstacleGrid,int m,int n,int row,int col,int[][] paths){
        if (row>=m||col>=n) {
            return 0;
        }
        if (obstacleGrid[row][col]==1) {
            return 0;
        }
        if (row==m-1&&col==n-1) {
            return 1;
        }
        int path = paths[row][col];
        if (path >=0) {
            return path;
        }
        int right = degrace(obstacleGrid,m, n, row , col+1, paths);

        int down = degrace(obstacleGrid,m, n, row+1, col , paths);
        int result = right + down;
        paths[row][col]=result;
        return result;
    }

    public static void main(String[] args) {
        UniqPath2 uniqPath=new UniqPath2();
        /*int[][] grid=new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };*/
        int[][] grid=new int[][]{
                {0,1}
        };
        int i = uniqPath.uniquePathsWithObstacles(grid);
        System.out.println(i);
    }
}
