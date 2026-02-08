package com.liyongquan.dp;


/**
 * 数组路径
 * https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
 * DFS算法+可行性减枝
 */
public class Shuzulujing {
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        int[][] visit = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (exist(board, word, visit, i, j, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, String word, int[][] visit,
                          int x, int y, int row, int col) {
        char c = word.charAt(0);
        if (x< 0||x>=row||y<0||y>=col) {
            return false;
        }
        if (visit[x][y]==1||board[x][y]!=c) {
            return false;
        }
        visit[x][y]=1;
        if (word.length()==1) {
            return true;
        }
        String subWord = word.substring(1);
        if (exist(board,subWord,visit,x,y-1,row,col)||
                exist(board,subWord,visit,x,y+1,row,col)||
                exist(board,subWord,visit,x-1,y,row,col)||
                exist(board,subWord,visit,x+1,y,row,col)) {
            return true;
        }
        visit[x][y]=0;
        return false;
    }

    public static void main(String[] args) {
        //char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        char[][] board = new char[][]{{'A','A'}};
        Shuzulujing su=new Shuzulujing();
        boolean result = su.exist(board, "AAA");
        System.out.println(result);
    }
}
