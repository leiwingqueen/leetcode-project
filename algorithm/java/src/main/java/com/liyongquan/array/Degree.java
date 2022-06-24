package com.liyongquan.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 * <p>
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1, 2, 2, 3, 1]
 * 输出: 2
 * 解释:
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2:
 * <p>
 * 输入: [1,2,2,3,1,4,2]
 * 输出: 6
 * 注意:
 * <p>
 * nums.length 在1到50,000区间范围内。
 * nums[i] 是一个在0到49,999范围内的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/degree-of-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Degree {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int c = count.getOrDefault(num, 0) + 1;
            count.put(num, c);
            if (c > max) {
                max = c;
            }
            if (!left.containsKey(num)) {
                left.put(num, i);
            }
            right.put(num, i);
        }
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> kv : count.entrySet()) {
            if (kv.getValue() == max) {
                int len = right.get(kv.getKey()) - left.get(kv.getKey()) + 1;
                if (len < min) {
                    min = len;
                }
            }
        }
        return min;
    }

    public static final int MAX_LEN = 50_000;

    /**
     * 滑动窗口解法。
     * <p>
     * 先统计数组的度，然后使用滑动窗口计算满足度的最小子串
     *
     * 时间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int findShortestSubArray2(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        //计算数组的度
        int[] cnt = new int[MAX_LEN];
        int degree = 0;
        for (int num : nums) {
            cnt[num]++;
            degree = Math.max(degree, cnt[num]);
        }
        //滑动窗口开始
        int l = 0, r = 0;
        int[] window = new int[MAX_LEN];
        int res = len;
        while (r < len) {
            window[nums[r]]++;
            r++;
            while (window[nums[r - 1]] == degree) {
                res = Math.min(res, r - l);
                window[nums[l]]--;
                l++;
            }
        }
        return res;
    }
}
