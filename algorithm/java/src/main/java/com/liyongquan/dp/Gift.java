package com.liyongquan.dp;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 *
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *
 *
 *
 * 提示：
 *
 *     0 < grid.length <= 200
 *     0 < grid[0].length <= 200
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Gift {
    /**
     * dp方程：f(i,j)=max{f(i-1,j),f(i,j-1)}+S[i][j]
     * 初始化：
     * i=0,j=0. f[0][0]=S[0][0]
     *
     * 遍历的过程可以使用BFS来实现,算法复杂度为O(m*n)
     *
     * BFS+减枝
     * @param grid
     * @return
     */
    public int maxValue(int[][] grid) {
        int row=grid.length;
        int col=grid[0].length;
        int[][] result=new int[row][col];
        Queue<int[]> queue=new LinkedList<>();
        queue.add(new int[]{0,0});
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int r=0;
            if (x==0&&y==0) {
                r=grid[0][0];
            }else if(x==0) {
                r=result[x][y-1]+grid[x][y];
            }else if(y==0) {
                r=result[x-1][y]+grid[x][y];
            }else{
                r=Math.max(result[x-1][y],result[x][y-1])+grid[x][y];
            }
            result[x][y]=r;
            //已经计算的点就不再计算
            if(x < row-1&&result[x+1][y]==0){
                queue.add(new int[]{x+1,y});
                //标记为已经进队列，不再重复计算
                result[x+1][y]=-1;
            }
            if(y< col-1&&result[x][y+1]==0){
                queue.add(new int[]{x,y+1});
                result[x][y+1]=-1;
            }
        }
        return result[row-1][col-1];
    }
}
