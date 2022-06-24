package com.liyongquan.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class Yundongfanwei {
    public int movingCount(int m, int n, int k) {
        //-1表示不能访问,1表示已访问,0为未访问的点
        int[][] visit=new int[m][n];
        //标记出所有不能访问的点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visit[i][j]=canVisit(i,j,k)?0:-1;
            }
        }
        //广度优先搜索
        int size=0;
        int x,y;
        x=y=0;
        Queue<Position> queue=new ArrayDeque<>(m*n);
        Position start=new Position();
        start.x=0;
        start.y=0;
        queue.offer(start);
        visit[0][0]=1;
        size++;
        while (!queue.isEmpty()){
            Position poll = queue.poll();
            Position left = move(poll.x, poll.y - 1, visit, m, n);
            if (left!=null) {
                queue.offer(left);
                size++;
            }
            Position right = move(poll.x, poll.y + 1, visit, m, n);
            if (right!=null) {
                queue.offer(right);
                size++;
            }
            Position up = move(poll.x-1, poll.y, visit, m, n);
            if (up!=null) {
                queue.offer(up);
                size++;
            }
            Position down = move(poll.x+1, poll.y, visit, m, n);
            if (down!=null) {
                queue.offer(down);
                size++;
            }
        }
        return size;
    }

    private Position move(int x,int y,int[][] visit,int m,int n){
        if(x<0||y<0||x>=m||y>=n){
            return null;
        }
        if (visit[x][y]==0) {
            Position position=new Position();
            position.x=x;
            position.y=y;
            visit[x][y]=1;
            return position;
        }
        return null;
    }

    private boolean canVisit(int i,int j,int k){
        int result=0;
        while (i>0){
            result+=i%10;
            i/=10;
        }
        while (j>0){
            result+=j%10;
            j/=10;
        }
        return result<=k;
    }

    class Position{
        int x;
        int y;
    }

    public static void main(String[] args) {
        Yundongfanwei yundongfanwei=new Yundongfanwei();
        int i = yundongfanwei.movingCount(2, 3, 1);
        System.out.println(i);
    }
}
