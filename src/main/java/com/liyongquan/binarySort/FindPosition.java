package com.liyongquan.binarySort;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindPosition {
    public int[] searchRange(int[] nums, int target) {
        int search = search(nums, 0, nums.length - 1, target);
        if (search < 0) {
            return new int[]{-1, -1};
        }
        int left, right;
        left = right = search;
        while (true) {
            boolean end = true;
            if (left - 1 >= 0 && nums[left - 1] == target) {
                left--;
                end = false;
            }
            if (right + 1 < nums.length && nums[right + 1] == target) {
                right++;
                end = false;
            }
            if (end) {
                break;
            }
        }
        return new int[]{left, right};
    }

    private int search(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        if (start == end) {
            return target == nums[start] ? start : -1;
        }
        int middle = (start + end) / 2;
        if (nums[middle] == target) {
            return middle;
        } else if (nums[middle] < target) {
            return search(nums, middle + 1, end, target);
        } else {
            return search(nums, start, middle - 1, target);
        }
    }
}
