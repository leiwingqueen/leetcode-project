package com.liyongquan.array;

public class GameOfLife {
    /**
     * 增加状态
     * 0--上一个&当前状态均为0
     * 1--上一个&当前状态均为1
     * 2--上一个状态为0，当前状态为1
     * 3--上一个状态为1，当前状态为0
     * @param board
     */
    public void gameOfLife(int[][] board) {
        int row=board.length;
        int col=board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int newStatus = lifeOrDead(board, i, j, row, col);
                board[i][j]=getNewStatus(board[i][j],newStatus);
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j]=backTo01(board[i][j]);
            }
        }
    }

    private int getNewStatus(int old,int news){
        if(old==0){
            return news==0?0:2;
        }
        return news==1?1:3;
    }

    private int backTo01(int status){
        return status==0||status==3?0:1;
    }

    private int lifeOrDead(int[][] board,int i,int j,int row,int col){
        int life = board[i][j];
        int arround2 = getArround2(board, i, j, row, col);
        if (life==1) {
            if(arround2<2){
                return 0;
            }else if(arround2>=2&&arround2<=3){
                return 1;
            }else {
                return 0;
            }
        }else{
            if(arround2==3){
                return 1;
            }
            return 0;
        }
    }

    private int getArround2(int[][] board, int i, int j, int row, int col) {
        int result=0;
        for (int k = j-1; k <= j + 1; k++) {
            result+=getArround(board,i-1,k,row,col);
            result+=getArround(board,i+1,k,row,col);
        }
        result+=getArround(board,i,j-1,row,col);
        result+=getArround(board,i,j+1,row,col);
        return result;
    }


    int getArround(int[][] board,int i,int j,int row,int col){
        if(i< 0||i>=row||j<0||j>=col) {
            return 0;
        }
        return (board[i][j]==1||board[i][j]==3)?1:0;
    }

    public static void main(String[] args) {
        int[][] board=new int[][]{
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        };
        GameOfLife life=new GameOfLife();
        life.gameOfLife(board);
        for (int[] ints : board) {
            for (int anInt : ints) {
                System.out.print(anInt+",");
            }
            System.out.println();
        }
    }


}
