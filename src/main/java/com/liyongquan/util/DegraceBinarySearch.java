package com.liyongquan.util;

/**
 * 二分查找递归实现方案
 */
public class DegraceBinarySearch implements IBinarySearch{
    @Override
    public int search(int[] nums, int target) {
        return search(nums,0,nums.length-1,target);
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
