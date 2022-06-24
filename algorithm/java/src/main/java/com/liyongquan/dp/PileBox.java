package com.liyongquan.dp;

import java.util.Arrays;

//堆箱子。给你一堆n个箱子，箱子宽 wi、深 di、高 hi。箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。实现一种方法，搭出最
//高的一堆箱子。箱堆的高度为每个箱子高度的总和。
//
// 输入使用数组[wi, di, hi]表示每个箱子。
//
// 示例1:
//
//  输入：box = [[1, 1, 1], [2, 2, 2], [3, 3, 3]]
// 输出：6
//
//
// 示例2:
//
//  输入：box = [[1, 1, 1], [2, 3, 4], [2, 6, 7], [3, 4, 5]]
// 输出：10
//
//
// 提示:
//
//
// 箱子的数目不大于3000个。
//
// Related Topics 动态规划 回溯算法
// 👍 43 👎 0

public class PileBox {
    /**
     * 先排序，确保排在 后面的箱子 不能放 前面的箱子 上面，简化后面的dp的过程
     * <p>
     * 我们假设f(n)表示前面n个箱子的最大高度，且我们要求最后一个箱子为box[n-1]
     * <p>
     * 我们需要求的解为
     * max{f(k)},1<=k<=n
     * <p>
     * dp方程：
     * f(n)=max{f(k)}+box[n-1],其中box[k]<box[n-1],1<=k<n
     *
     * 时间复杂度O(n^2)
     * 空间复杂度O(n)
     *
     * @param box
     * @return
     */
    public int pileBox(int[][] box) {
        int len = box.length;
        if (len == 0) {
            return 0;
        }
        //先排序，确保排在 后面的箱子 不能放 前面的箱子 上面
        Arrays.sort(box, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : (o1[1] != o2[1] ? o1[1] - o2[1] : o1[2] - o2[2]));
        int[] dp = new int[len];
        dp[0] = box[0][2];
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            dp[i] = box[i][2];
            for (int k = 0; k < i; k++) {
                if (box[k][0] < box[i][0] && box[k][1] < box[i][1] && box[k][2] < box[i][2]) {
                    dp[i] = Math.max(dp[k] + box[i][2], dp[i]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
