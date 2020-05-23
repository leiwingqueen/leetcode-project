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
        int left = search(nums, target, true);
        if (left < 0) {
            return new int[]{-1, -1};
        }
        int right = search(nums, target, false);
        return new int[]{left, right};
//        int left, right;
//        left = right = left;
//        while (true) {
//            boolean end = true;
//            if (left - 1 >= 0 && nums[left - 1] == target) {
//                left--;
//                end = false;
//            }
//            if (right + 1 < nums.length && nums[right + 1] == target) {
//                right++;
//                end = false;
//            }
//            if (end) {
//                break;
//            }
//        }
//        return new int[]{left, right};
    }

    private int search(int[] nums, int target, boolean l) {
        if (nums.length <= 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                if (l) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
