package com.liyongquan.array;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 739. 每日温度
 * <p>
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DailyTemperatures {
    /**
     * 先尝试暴力解法
     * <p>
     * 暴力居然还能过
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        if (len == 0) {
            return new int[]{};
        }
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            int j = i + 1;
            while (j < len && T[j] <= T[i]) {
                j++;
            }
            if (j < len) {
                res[i] = j - i;
            }
        }
        return res;
    }

    /**
     * 单调栈
     *
     * @param T
     * @return
     */
    public int[] dailyTemperatures2(int[] T) {
        int len = T.length;
        if (len == 0) {
            return new int[]{};
        }
        int[] res = new int[len];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && T[stack.peekLast()] < T[i]) {
                Integer idx = stack.pollLast();
                res[idx] = i - idx;
            }
            stack.offerLast(i);
        }
        return res;
    }
}
