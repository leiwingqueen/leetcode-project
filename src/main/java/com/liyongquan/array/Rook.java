package com.liyongquan.array;


public class Rook {
    public int numRookCaptures(char[][] board) {
        int x,y;
        x=y=0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j]=='R') {
                    x=i;
                    y=j;
                    break;
                }
            }
        }
        int result=0;
        result += move(board, x, y, (x1, y1) -> new int[]{x1 -1, y1});
        result += move(board, x, y, (x1, y1) -> new int[]{x1 , y1-1});
        result += move(board, x, y, (x1, y1) -> new int[]{x1 +1, y1});
        result += move(board, x, y, (x1, y1) -> new int[]{x1 , y1+1});
        return result;
    }

    private int move(char[][] board, int x, int y, Movement movement) {
        do{
            int[] move = movement.move(x, y);
            x=move[0];
            y=move[1];
            if (x<0||x>=board.length||y<0||y>=board.length) {
                return 0;
            }
            if (board[x][y]=='B') {
                return 0;
            }
            if (board[x][y]=='p') {
                return 1;
            }
        }while (true);
    }

    interface Movement {
        int[] move(int x,int y);
    }



    public static void main(String[] args) {
        char[][] s=new char[][]{{'.','.','.','.','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'.','.','.','R','.','.','.','p'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','p','.','.','.','.'},{'.','.','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','.'}};
        Rook rook=new Rook();
        int i = rook.numRookCaptures(s);
        System.out.println(i);
    }
}
