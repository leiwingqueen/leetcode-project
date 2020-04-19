package com.liyongquan.array;

/**
 * 统计一个数字在排序数组中出现的次数。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * <p>
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= 数组长度 <= 50000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumCounter {
    /**
     * 数量级在5w以内，我们可以直接遍历一次即可
     *
     * @param nums
     * @param target
     * @return
     */
    public int search1(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            count += (num == target ? 1 : 0);
        }
        return count;
    }

    /**
     * 由于数组是有序的，因此我们可以考虑使用二分查找进行优化
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int i = find(nums, 0, nums.length - 1, target);
        if (i < 0) {
            return 0;
        }
        int count = 1;
        int right = i + 1;
        while (right <= nums.length - 1 && nums[right] == target) {
            count += 1;
            right++;
        }
        int left = i - 1;
        while (left >= 0 && nums[left] == target) {
            count += 1;
            left--;
        }
        return count;
    }

    private int find(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        if (left == right) {
            return nums[left] == target ? left : -1;
        }
        int middle = (left + right) / 2;
        if (nums[middle] == target) {
            return middle;
        } else if (nums[middle] > target) {
            return find(nums, left, middle - 1, target);
        } else {
            return find(nums, middle + 1, right, target);
        }
    }
}
