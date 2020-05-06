package com.liyongquan.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 颜色填充。编写函数，实现许多图片编辑软件都支持的“颜色填充”功能。给定一个屏幕（以二维数组表示，元素为颜色值）、一个点和一个新的颜色值，将新颜色值填入这个点的周围区域，直到原来的颜色值全都改变。
 * <p>
 * 示例1:
 * <p>
 * 输入：
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * 输出：[[2,2,2],[2,2,0],[2,0,1]]
 * 解释:
 * 在图像的正中间，(坐标(sr,sc)=(1,1)),
 * 在路径上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，
 * 因为它不是在上下左右四个方向上与初始点相连的像素点。
 * 说明：
 * <p>
 * image 和 image[0] 的长度在范围 [1, 50] 内。
 * 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
 * image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/color-fill-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FillColor {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int row = image.length;
        int col = image[0].length;
        int[][] path = new int[row][col];
        int oldColor = image[sr][sc];
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(sr, sc));
        while (!queue.isEmpty()) {
            Position poll = queue.poll();
            if (poll.x >= 0 && poll.y >= 0 && poll.x < row && poll.y < col
                    && path[poll.x][poll.y] == 0 && image[poll.x][poll.y] == oldColor) {
                path[poll.x][poll.y] = 1;
                image[poll.x][poll.y] = newColor;
                queue.add(new Position(poll.x - 1, poll.y));
                queue.add(new Position(poll.x + 1, poll.y));
                queue.add(new Position(poll.x, poll.y - 1));
                queue.add(new Position(poll.x, poll.y + 1));
            }
        }
        return image;
    }

    private class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        FillColor fillColor = new FillColor();
        int[][] ints = fillColor.floodFill(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2);
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.print(ints[i][j]+",");
            }
            System.out.println();
        }
    }
}
