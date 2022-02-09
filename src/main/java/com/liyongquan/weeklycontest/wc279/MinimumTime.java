package com.liyongquan.weeklycontest.wc279;

//2167. 移除所有载有违禁货物车厢所需的最少时间
//给你一个下标从 0 开始的二进制字符串 s ，表示一个列车车厢序列。s[i] = '0' 表示第 i 节车厢 不 含违禁货物，而 s[i] = '1' 表示第 i 节车厢含违禁货物。
//
//作为列车长，你需要清理掉所有载有违禁货物的车厢。你可以不限次数执行下述三种操作中的任意一个：
//
//从列车 左 端移除一节车厢（即移除 s[0]），用去 1 单位时间。
//从列车 右 端移除一节车厢（即移除 s[s.length - 1]），用去 1 单位时间。
//从列车车厢序列的 任意位置 移除一节车厢，用去 2 单位时间。
//返回移除所有载有违禁货物车厢所需要的 最少 单位时间数。
//
//注意，空的列车车厢序列视为没有车厢含违禁货物。
//
// 
//
//示例 1：
//
//输入：s = "1100101"
//输出：5
//解释：
//一种从序列中移除所有载有违禁货物的车厢的方法是：
//- 从左端移除一节车厢 2 次。所用时间是 2 * 1 = 2 。
//- 从右端移除一节车厢 1 次。所用时间是 1 。
//- 移除序列中间位置载有违禁货物的车厢。所用时间是 2 。
//总时间是 2 + 1 + 2 = 5 。
//
//一种替代方法是：
//- 从左端移除一节车厢 2 次。所用时间是 2 * 1 = 2 。
//- 从右端移除一节车厢 3 次。所用时间是 3 * 1 = 3 。
//总时间也是 2 + 3 = 5 。
//
//5 是移除所有载有违禁货物的车厢所需要的最少单位时间数。
//没有其他方法能够用更少的时间移除这些车厢。
//示例 2：
//
//输入：s = "0010"
//输出：2
//解释：
//一种从序列中移除所有载有违禁货物的车厢的方法是：
//- 从左端移除一节车厢 3 次。所用时间是 3 * 1 = 3 。
//总时间是 3.
//
//另一种从序列中移除所有载有违禁货物的车厢的方法是：
//- 移除序列中间位置载有违禁货物的车厢。所用时间是 2 。
//总时间是 2.
//
//另一种从序列中移除所有载有违禁货物的车厢的方法是：
//- 从右端移除一节车厢 2 次。所用时间是 2 * 1 = 2 。
//总时间是 2.
//
//2 是移除所有载有违禁货物的车厢所需要的最少单位时间数。
//没有其他方法能够用更少的时间移除这些车厢。
// 
//
//提示：
//
//1 <= s.length <= 2 * 105
//s[i] 为 '0' 或 '1'
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-time-to-remove-all-cars-containing-illegal-goods
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class MinimumTime {
    /**
     * 回溯？
     *
     * 必然超时了
     *
     * @param s
     * @return
     */
    public int minimumTime(String s) {
        return backtrace(s.toCharArray(), 0, s.length() - 1);
    }

    private int backtrace(char[] arr, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return arr[l] == '0' ? 0 : 1;
        }
        //贪心，优先处理边界的车厢
        if (arr[l] == '1') {
            return backtrace(arr, l + 1, r) + 1;
        }
        if (arr[r] == '1') {
            return backtrace(arr, l, r - 1) + 1;
        }
        //先尝试移除左边最近的车厢
        int idx = l;
        while (idx <= r && arr[idx] == '0') {
            idx++;
        }
        if (idx > r) {
            return 0;
        }
        //从中间移除
        arr[idx] = '0';
        int sub1 = backtrace(arr, l + 1, r) + 2;
        arr[idx] = '1';
        //从左边一直移除
        int sub2 = backtrace(arr, idx + 1, r) + (idx - l + 1);
        //溢出靠近右边的车厢
        idx = r;
        while (idx >= l && arr[idx] == '0') {
            idx--;
        }
        arr[idx] = '0';
        int sub3 = backtrace(arr, l, r - 1) + 2;
        arr[idx] = '1';
        int sub4 = backtrace(arr, l, idx - 1) + (r - idx + 1);
        return Math.min(Math.min(Math.min(sub1, sub2), sub3), sub4);
    }
}
