package com.liyongquan.math;

//你有 n 台电脑。给你整数 n 和一个下标从 0 开始的整数数组 batteries ，其中第 i 个电池可以让一台电脑 运行 batteries[i] 分钟。你想使用这些电池让 全部 n 台电脑 同时 运行。
//
//一开始，你可以给每台电脑连接 至多一个电池 。然后在任意整数时刻，你都可以将一台电脑与它的电池断开连接，并连接另一个电池，你可以进行这个操作 任意次 。新连接的电池可以是一个全新的电池，也可以是别的电脑用过的电池。断开连接和连接新的电池不会花费任何时间。
//
//注意，你不能给电池充电。
//
//请你返回你可以让 n 台电脑同时运行的 最长 分钟数。
//
// 
//
//示例 1：
//
//
//
//输入：n = 2, batteries = [3,3,3]
//输出：4
//解释：
//一开始，将第一台电脑与电池 0 连接，第二台电脑与电池 1 连接。
//2 分钟后，将第二台电脑与电池 1 断开连接，并连接电池 2 。注意，电池 0 还可以供电 1 分钟。
//在第 3 分钟结尾，你需要将第一台电脑与电池 0 断开连接，然后连接电池 1 。
//在第 4 分钟结尾，电池 1 也被耗尽，第一台电脑无法继续运行。
//我们最多能同时让两台电脑同时运行 4 分钟，所以我们返回 4 。
//示例 2：
//
//
//
//输入：n = 2, batteries = [1,1,1,1]
//输出：2
//解释：
//一开始，将第一台电脑与电池 0 连接，第二台电脑与电池 2 连接。
//一分钟后，电池 0 和电池 2 同时耗尽，所以你需要将它们断开连接，并将电池 1 和第一台电脑连接，电池 3 和第二台电脑连接。
//1 分钟后，电池 1 和电池 3 也耗尽了，所以两台电脑都无法继续运行。
//我们最多能让两台电脑同时运行 2 分钟，所以我们返回 2 。
// 
//
//提示：
//
//1 <= n <= batteries.length <= 105
//1 <= batteries[i] <= 109
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-running-time-of-n-computers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Arrays;

public class MaxRunTime {
    /**
     * 暴力解法
     *
     * 超时
     *
     * @param n
     * @param batteries
     * @return
     */
    public long maxRunTime(int n, int[] batteries) {
        int len = batteries.length;
        Arrays.sort(batteries);
        int sum = 0;
        while (batteries[len - n] > 0) {
            int pre = 0;
            if (len - n - 1 >= 0) {
                pre = batteries[len - n - 1];
            }
            //尽可能相减
            int desc = batteries[len - n] - pre;
            for (int i = len - n; i < len; i++) {
                batteries[i] -= desc;
            }
            sum += desc;
            if (batteries[len - n] > 0) {
                for (int i = len - n; i < len; i++) {
                    batteries[i]--;
                }
                sum++;
            }
            //重新排序
            Arrays.sort(batteries);
        }
        return sum;
    }

    /**
     * 贪心
     * <p>
     * 为了减少批量减法的时间复杂度，我们把数据改成用前缀和来进行计算
     *
     * @param n
     * @param batteries
     * @param k
     * @return
     */
    private boolean check(int n, int[] batteries, int k) {
        int len = batteries.length;
        Arrays.sort(batteries);
        //改成使用一个差数列来保存
        int[] arr = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            if (i == 0) {
                arr[i] = batteries[i - 1];
            } else {
                arr[i] = batteries[i - 1] - arr[i];
            }
        }
        return true;
    }
}
