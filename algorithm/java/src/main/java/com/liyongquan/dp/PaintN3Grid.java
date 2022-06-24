package com.liyongquan.dp;

/**
 * 你有一个 n x 3 的网格图 grid ，你需要用 红，黄，绿 三种颜色之一给每一个格子上色，且确保相邻格子颜色不同（也就是有相同水平边或者垂直边的格子颜色不同）。
 * <p>
 * 给你网格图的行数 n 。
 * <p>
 * 请你返回给 grid 涂色的方案数。由于答案可能会非常大，请你返回答案对 10^9 + 7 取余的结果。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 1
 * 输出：12
 * 解释：总共有 12 种可行的方法：
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 2
 * 输出：54
 * 示例 3：
 * <p>
 * 输入：n = 3
 * 输出：246
 * 示例 4：
 * <p>
 * 输入：n = 7
 * 输出：106494
 * 示例 5：
 * <p>
 * 输入：n = 5000
 * 输出：30228214
 *  
 * <p>
 * 提示：
 * <p>
 * n == grid.length
 * grid[i].length == 3
 * 1 <= n <= 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-ways-to-paint-n-3-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PaintN3Grid {
    public int numOfWays(int n) {
        int mod = 1000000007;
        //一行一共只有12种可行的场景
        int[] types = {
                12,
                10,
                20,
                21,
                101,
                102,
                120,
                121,
                201,
                202,
                210,
                212,
        };
        int[] pre = new int[types.length];
        for (int i = 0; i < types.length; i++) {
            pre[i] = 1;
        }
        int[] cur = new int[types.length];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < types.length; j++) {
                for (int k = 0; k < types.length; k++) {
                    if (check(types[j], types[k])) {
                        cur[j] += pre[k];
                        cur[j] %= mod;
                    }
                }
            }
            //更新pre
            pre = cur;
            cur = new int[types.length];
        }
        int sum = 0;
        for (int i = 0; i < types.length; i++) {
            sum += pre[i];
            sum %= mod;
        }
        return sum;
    }

    public boolean check(int num1, int num2) {
        int mod = 100;
        while (mod > 0) {
            if (num1 / mod == num2 / mod) {
                return false;
            }
            num1 %= mod;
            num2 %= mod;
            mod /= 10;
        }
        return true;
    }
}
