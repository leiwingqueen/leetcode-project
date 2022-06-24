package com.liyongquan.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class Land {
    public int maxDistance(int[][] grid) {
        int maxDis=-1;
        int row,col;
        row=grid.length;
        col=grid[0].length;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]!=0) {
                    continue;
                }
                int[][] visit=new int[row][col];
                //System.out.println(String.format("开始查找===========.i:%s,j:%s",i,j));
                visit[i][j]=1;
                int path = findPath(grid, i, j);
                //System.out.println(String.format("最短路径.i:%s,j:%s,path:%s",i,j,path));
                if (path>maxDis) {
                    maxDis=path;
                }
            }
        }
        return maxDis;
    }

    public int findPath(int[][] grid,int i,int j){
        int row,col;
        row=grid.length;
        col=grid[0].length;
        int[][] visit=new int[row][col];
        Queue<Position> queue=new ArrayDeque<>(row*col);
        Position position=new Position(i,j,0);
        queue.offer(position);
        while (!queue.isEmpty()){
            Position poll = queue.poll();
            //System.out.println(String.format("出队列...i:%s,j:%s",poll.x,poll.y));
            int x=poll.x;
            int y=poll.y;
            visit[x][y]=1;

            if (move1(grid, x-1, y, row, col, visit, queue, poll)) {
                return poll.depth + 1;
            }
            if (move1(grid, x+1, y, row, col, visit, queue, poll)) {
                return poll.depth + 1;
            }
            if (move1(grid, x, y-1, row, col, visit, queue, poll)) {
                return poll.depth + 1;
            }
            if (move1(grid, x, y+1, row, col, visit, queue, poll)) {
                return poll.depth + 1;
            }
        }
        return -1;
    }

    private boolean move1(int[][] grid, int i, int j, int row, int col, int[][] visit, Queue<Position> queue, Position poll) {
        if (i<0||i>=row||j<0||j>=col||visit[i][j]==1) {
            return false;
        }
        if (grid[i][j]==1) {
            return true;
        }
        visit[i][j]=1;
        Position up=new Position(i,j,poll.depth+1);
        //System.out.println(String.format("入队列...i:%s,j:%s",i,j));
        queue.offer(up);
        return false;
    }

    class Position{
        int x;
        int y;
        int depth;
        public Position(int x,int y,int depth){
            this.x=x;
            this.y=y;
            this.depth=depth;
        }
    }

    private void print(int[][]a){
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j]+",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Land land=new Land();
        int[][] grid=new int[][]{
                {1,0,0},
                {0,0,0},
                {0,0,0}
        };
        int i = land.maxDistance(grid);
        System.out.println(i);
    }
}
