package com.liyongquan.square;

public class Shape {
    public static class Solution {
        public int surfaceArea(int[][] grid) {
            int result=0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    //暴露在外面的总面积
                    int top = grid[i][j];
                    if (top==0) {
                        continue;
                    }
                    int square = top * 4 + 2;
                    //减去相邻的面积
                    square=square-getTouch(grid,top,i,j+1)
                            -getTouch(grid,top,i,j-1)-getTouch(grid,top,i+1,j)-getTouch(grid,top,i-1,j);
                    result+=square;
                }
            }
            return result;
        }
    }

    private static int getTouch(int[][] grid,int top,int i,int j){
        if(i<0||i>=grid.length||j<0||j>=grid.length){
            return 0;
        }
        int t2 = grid[i][j];
        return t2 > top ? top : t2;
    }

    public static void main(String[] args) {
        //int[][] grid=new int[][]{{1,2},{3,4}};
        int[][] grid = {{1, 0}, {0, 2}};
        Solution solution=new Solution();
        int i = solution.surfaceArea(grid);
        System.out.println(i);
    }
}
