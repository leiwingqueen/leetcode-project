package com.liyongquan.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个整数数组 a，其中1 ≤ a[i] ≤ n （n为数组长度）, 其中有些元素出现两次而其他元素出现一次。
 * <p>
 * 找到所有出现两次的元素。
 * <p>
 * 你可以不用到任何额外空间并在O(n)时间复杂度内解决这个问题吗？
 * <p>
 * 示例：
 * <p>
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [2,3]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Duplicates {
    /**
     * 先使用额外空间解决
     * <p>
     * 空间复杂度O(n)，时间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> c : count.entrySet()) {
            if (c.getValue() >= 2) {
                result.add(c.getKey());
            }
        }
        return result;
    }

    /**
     * 使用翻转的方式解决
     *
     * @param nums
     * @return
     */
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            int pos = Math.abs(num);
            if (nums[pos - 1] < 0) {
                result.add(pos);
            } else {
                nums[pos - 1] = -nums[pos - 1];
            }
        }
        return result;
    }
}
