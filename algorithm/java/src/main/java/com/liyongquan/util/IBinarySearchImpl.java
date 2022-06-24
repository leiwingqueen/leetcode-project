package com.liyongquan.util;

/**
 * 二分查找非递归实现
 */
public class IBinarySearchImpl implements IBinarySearch {
    @Override
    public int search(int[] nums, int target) {
        if (nums.length<=0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return nums[left] == target ? left : -1;
    }
}
