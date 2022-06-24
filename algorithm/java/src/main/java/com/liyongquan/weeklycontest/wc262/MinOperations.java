package com.liyongquan.weeklycontest.wc262;

import java.util.Arrays;

/**
 * @author liyongquan
 * @date 2021/10/10
 */
public class MinOperations {
    public int minOperations(int[][] grid, int x) {
        int row = grid.length, col = grid[0].length;
        if (row == 1 && col == 1) {
            return 0;
        }
        int len = row * col;
        int[] arr = new int[len];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i * col + j] = grid[i][j];
            }
        }
        Arrays.sort(arr);
        //计算相邻数字的差距
        int[] dis = new int[len - 1];
        //前缀和
        int[] prefix = new int[len];
        prefix[0] = 0;
        for (int i = 1; i < len; i++) {
            int d = arr[i] - arr[i - 1];
            if (d % x != 0) {
                return -1;
            }
            dis[i - 1] = d / x;
            prefix[i] = prefix[i - 1] + dis[i - 1];
        }
        //全部设置为最大值的步长总和
        int sum = 0;
        for (int i : prefix) {
            sum += i;
        }
        int idx = len - 2;
        //左右两边的数字数量
        int lCnt = len - 1;
        int rCnt = 1;
        while (idx >= 0) {
            int s = sum;
            s += rCnt * dis[idx];
            s -= lCnt * dis[idx];
            idx--;
            if (s > sum) {
                break;
            }
            sum = s;
        }
        return sum;
    }
}
