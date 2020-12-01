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

    /**
     * 两次二分查找，时间复杂度O(logn)，空间复杂度O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange2(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        //先找到左边界
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int middle = l + (r - l) / 2;
            if (nums[middle] == target) {
                //看能不能左移一步
                if (middle == l || nums[middle - 1] != target) {
                    l = middle;
                    break;
                }
                r = middle - 1;
            } else if (nums[middle] > target) {
                r = middle - 1;
            } else {
                l = middle + 1;
            }
        }
        if (nums[l] != target) {
            return new int[]{-1, -1};
        }
        //再尝试找右边界
        int left = l;
        r = nums.length - 1;
        while (l < r) {
            int middle = l + (r - l) / 2;
            if (nums[middle] == target) {
                //看能不能再右移一步
                if (middle == r || nums[middle + 1] != target) {
                    r = middle;
                    break;
                }
                l = middle + 1;
            } else if (nums[middle] > target) {
                r = middle - 1;
            } else {
                l = middle + 1;
            }
        }
        return new int[]{left, r};
    }
}
