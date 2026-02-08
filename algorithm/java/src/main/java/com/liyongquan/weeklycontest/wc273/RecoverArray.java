package com.liyongquan.weeklycontest.wc273;

//Alice 有一个下标从 0 开始的数组 arr ，由 n 个正整数组成。她会选择一个任意的 正整数 k 并按下述方式创建两个下标从 0 开始的新整数数组 lower 和 higher ：
//
//对每个满足 0 <= i < n 的下标 i ，lower[i] = arr[i] - k
//对每个满足 0 <= i < n 的下标 i ，higher[i] = arr[i] + k
//不幸地是，Alice 丢失了全部三个数组。但是，她记住了在数组 lower 和 higher 中出现的整数，但不知道每个整数属于哪个数组。请你帮助 Alice 还原原数组。
//
//给你一个由 2n 个整数组成的整数数组 nums ，其中 恰好 n 个整数出现在 lower ，剩下的出现在 higher ，还原并返回 原数组 arr 。如果出现答案不唯一的情况，返回 任一 有效数组。
//
//注意：生成的测试用例保证存在 至少一个 有效数组 arr 。
//
// 
//
//示例 1：
//
//输入：nums = [2,10,6,4,8,12]
//输出：[3,7,11]
//解释：
//如果 arr = [3,7,11] 且 k = 1 ，那么 lower = [2,6,10] 且 higher = [4,8,12] 。
//组合 lower 和 higher 得到 [2,6,10,4,8,12] ，这是 nums 的一个排列。
//另一个有效的数组是 arr = [5,7,9] 且 k = 3 。在这种情况下，lower = [2,4,6] 且 higher = [8,10,12] 。
//示例 2：
//
//输入：nums = [1,1,3,3]
//输出：[2,2]
//解释：
//如果 arr = [2,2] 且 k = 1 ，那么 lower = [1,1] 且 higher = [3,3] 。
//组合 lower 和 higher 得到 [1,1,3,3] ，这是 nums 的一个排列。
//注意，数组不能是 [1,3] ，因为在这种情况下，获得 [1,1,3,3] 唯一可行的方案是 k = 0 。
//这种方案是无效的，k 必须是一个正整数。
//示例 3：
//
//输入：nums = [5,435]
//输出：[220]
//解释：
//唯一可行的组合是 arr = [220] 且 k = 215 。在这种情况下，lower = [5] 且 higher = [435] 。
// 
//
//提示：
//
//2 * n == nums.length
//1 <= n <= 1000
//1 <= nums[i] <= 109
//生成的测试用例保证存在 至少一个 有效数组 arr
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/recover-the-original-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;
import java.util.stream.Collectors;

public class RecoverArray {

    private static class Pair {
        boolean key;
        int[] value;
        Pair(boolean key, int[] value) {
            this.key = key;
            this.value = value;
        }
    }
    /**
     * 还差最后一个用例
     *
     * @param nums
     * @return
     */
    public int[] recoverArray(int[] nums) {
        //统计两两数字的差，如果差异的数量<n，则为不可能解
        Map<Integer, Integer> sub = new HashMap<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int dis = nums[j] - nums[i];
                sub.put(dis, sub.getOrDefault(dis, 0) + 1);
            }
        }
        //贪心，先从数量多的情况开始
        List<int[]> list = sub.entrySet().stream().map(entry -> new int[]{entry.getKey(), entry.getValue()}).collect(Collectors.toList());
        list.sort((o1, o2) -> o2[1] - o1[1]);
        for (int[] l : list) {
            int k = l[0];
            int cnt = l[1];
            if (k > 0 && k % 2 == 0 && cnt >= len / 2) {
                Pair check = check(nums, k / 2);
                if (check.key) {
                    return check.value;
                }
            }
        }
        return new int[]{};
    }

    /**
     * 检查逻辑
     * <p>
     * 时间复杂度O(n)
     *
     * @param k
     * @return
     */
    private Pair check(int[] nums, int k) {
        int[] res = new int[nums.length / 2];
        TreeMap<Integer, Integer> mp = new TreeMap<>();
        for (int num : nums) {
            mp.put(num, mp.getOrDefault(num, 0) + 1);
        }
        int idx = 0;
        while (mp.size() > 0) {
            Integer low = mp.firstKey();
            int high = low + 2 * k;
            if (!mp.containsKey(high)) {
                return new Pair(false, null);
            }
            res[idx++] = low + k;
            if (mp.get(low) == 1) {
                mp.remove(low);
            } else {
                mp.put(low, mp.get(low) - 1);
            }
            if (mp.get(high) == 1) {
                mp.remove(high);
            } else {
                mp.put(high, mp.get(high) - 1);
            }
        }
        return new Pair(true, res);
    }
}
