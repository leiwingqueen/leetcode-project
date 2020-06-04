package com.liyongquan.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Sum4num {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int[] ints = Arrays.stream(nums).sorted().toArray();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < ints.length - 3; i++) {
            List<List<Integer>> lists = threeSum(ints, target - ints[i], i + 1, ints.length - 1);
            for (List<Integer> list : lists) {
                List<Integer> r = new ArrayList<>();
                r.add(nums[i]);
                r.addAll(list);
                result.add(r);
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum(int[] ints, int target, int start, int end) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = start; i <= end - 2; i++) {
            if (ints[i] >= 0 && ints[i] > target) {
                break;
            }
            int left = i + 1, right = ints.length - 1;
            while (left < right) {
                int sum = ints[i] + ints[left] + ints[right];
                if (sum == target) {
                    result.add(Arrays.asList(ints[i], ints[left], ints[right]));
                    left++;
                    right--;
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }
}
