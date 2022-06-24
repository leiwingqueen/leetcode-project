package com.liyongquan.dp;

public class UniqPath {
    public int uniquePaths(int m, int n) {
        int[][] paths=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                paths[i][j]=-1;
            }
        }
        return degrace(m,n,0,0,paths);
    }

    public int degrace(int m,int n,int row,int col,int[][] paths){
        if (row==m-1&&col==n-1) {
            return 1;
        }
        if (row>=m||col>=n) {
            return 0;
        }
        int path = paths[row][col];
        if (path >=0) {
            return path;
        }
        int right = degrace(m, n, row , col+1, paths);

        int down = degrace(m, n, row+1, col , paths);
        int result = right + down;
        paths[row][col]=result;
        return result;
    }

    public static void main(String[] args) {
        UniqPath uniqPath=new UniqPath();
        int i = uniqPath.uniquePaths(7, 3);
        System.out.println(i);
    }
}
