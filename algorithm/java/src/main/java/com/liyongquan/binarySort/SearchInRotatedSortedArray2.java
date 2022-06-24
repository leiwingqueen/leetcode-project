package com.liyongquan.binarySort;

//已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
//
// 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums
//[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,
//2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
//
// 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 targ
//et ，则返回 true ，否则返回 false 。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,5,6,0,0,1,2], target = 0
//输出：true
//
//
// 示例 2：
//
//
//输入：nums = [2,5,6,0,0,1,2], target = 3
//输出：false
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 5000
// -104 <= nums[i] <= 104
// 题目数据保证 nums 在预先未知的某个下标上进行了旋转
// -104 <= target <= 104
//
//
//
//
// 进阶：
//
//
// 这是 搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素。
// 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
//
// Related Topics 数组 二分查找
// 👍 347 👎 0

import com.liyongquan.math.MultiplyStrings;

public class SearchInRotatedSortedArray2 {
    /**
     * 先找到翻转点，然后再二分
     * <p>
     * 当所有数值相等的时候只能退化成线性查找
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        int len = nums.length;
        if (len == 1) {
            return target == nums[0];
        }
        int l = 0, r = len - 1;
        //找到第一个nums[i]<nums[i-1]，不存在取第一个数字
        while (l < r) {
            int middle = (l + r) / 2;
            if (nums[middle] > nums[0]) {
                l = middle + 1;
            } else if (nums[middle] < nums[0]) {
                r = middle;
            } else {
                //分别向左右线性查找
                int p = middle - 1;
                while (p >= l && nums[p] == nums[0]) {
                    p--;
                }
                if (p >= l) {
                    if (nums[p] > nums[0]) {
                        l = middle + 1;
                    } else {
                        r = p;
                    }
                    continue;
                }
                p = middle + 1;
                while (p <= r && nums[p] == nums[0]) {
                    p++;
                }
                if (p <= r) {
                    if (nums[p] > nums[0]) {
                        l = p + 1;
                    } else {
                        //当前即为结果
                        l = p;
                        break;
                    }
                    continue;
                }
                //剩下的全部相同
                break;
            }
        }
        if (l >= len) {
            l = 0;
        }
        if (target > nums[len - 1]) {
            return binarySearch(nums, 0, l - 1, target);
        } else {
            return binarySearch(nums, l, len - 1, target);
        }
    }

    private boolean binarySearch(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int middle = (l + r) / 2;
            if (target == nums[middle]) {
                return true;
            } else if (target < nums[middle]) {
                r = middle - 1;
            } else {
                l = middle + 1;
            }
        }
        return false;
    }

    /**
     * 总算通过了，二分其实边界情况很多，不容易通过
     * <p>
     * 极端的情况所有数字相等，退化成线性查找
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search2(int[] nums, int target) {
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //消除掉左右两边相等的数字
            while (l <= mid && nums[l] == nums[mid]) {
                l++;
            }
            while (r >= mid && nums[r] == nums[mid]) {
                r--;
            }
            if (l > r) {
                return false;
            }
            //mid的位置不在[l,r]之间
            if (mid < l || mid > r) {
                continue;
            }
            //左右两边至少有一个是满足升序条件的
            if (nums[mid] < target) {
                //左边满足升序
                if (nums[mid] > nums[l]) {
                    l = mid + 1;
                } else {
                    //右边满足升序
                    if (nums[r] == target) {
                        return true;
                    } else if (nums[r] > target) {
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
            } else {
                //左边满足升序
                if (nums[mid] > nums[l]) {
                    if (nums[l] == target) {
                        return true;
                    } else if (nums[l] < target) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    //右边满足升序
                    r = mid - 1;
                }
            }
        }
        return false;
    }
}
