package com.liyongquan.array;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * <p>
 * 示例 2：
 * <p>
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PrintArray {
    public int[] spiralOrder(int[][] matrix) {

        //0--上,1--右,2--下,3--左，这个设计是比较巧妙的，这样碰壁后的下一个方向就可以通过(direction+1)%4来得到
        int direction = 3;
        int row = matrix.length;
        if (row<=0) {
            return new int[]{};
        }
        int col = matrix[0].length;
        int[][] visit = new int[row][col];
        int x = 0, y = 0;
        int[] result=new int[row*col];
        int i=0;
        visit[x][y]=1;
        result[i]=matrix[x][y];
        while (canMove(x-1,y,row,col,visit)||canMove(x+1,y,row,col,visit)
                ||canMove(x,y-1,row,col,visit)||canMove(x,y+1,row,col,visit)){
            boolean success=true;
            if (direction==0&&canMove(x-1,y,row,col,visit)) {
                x-=1;
            }else if(direction==1&&canMove(x,y+1,row,col,visit)){
                y+=1;
            }else if(direction==2&&canMove(x+1,y,row,col,visit)){
                x+=1;
            }else if(direction==3&&canMove(x,y-1,row,col,visit)){
                y-=1;
            }else {
                direction=(direction+1)%4;
                success=false;
            }
            if (success) {
                i++;
                visit[x][y]=1;
                result[i]=matrix[x][y];
            }
        }
        return result;
    }

    private boolean canMove(int x, int y, int row, int col, int[][] visit) {
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return false;
        }
        return visit[x][y]==0;
    }

    public static void main(String[] args) {
        int[][] matrix=new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        PrintArray array=new PrintArray();
        int[] ints = array.spiralOrder(matrix);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
