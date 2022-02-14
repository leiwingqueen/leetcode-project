package com.liyongquan.binarySort;

//540. 有序数组中的单一元素
//给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
//
//请你找出并返回只出现一次的那个数。
//
//你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
//
// 
//
//示例 1:
//
//输入: nums = [1,1,2,3,3,4,4,8,8]
//输出: 2
//示例 2:
//
//输入: nums =  [3,3,7,7,10,11,11]
//输出: 10
// 
//
//提示:
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class SingleNonDuplicate {
    /**
     * 数学解法
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor;
    }

    /**
     * 二分查找
     * <p>
     * 选择一个点作为分割点，假设分割点就是唯一的数字，那么直接范围，否则唯一数字必然在奇数的数量的一边
     *
     * @param nums
     * @return
     */
    public int singleNonDuplicate2(int[] nums) {
        int len = nums.length;
        int l = 0, r = len - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            //分割点就是唯一数字
            if ((mid - 1 < 0 || nums[mid] != nums[mid - 1]) && (mid + 1 >= len || nums[mid] != nums[mid + 1])) {
                return nums[mid];
            } else {
                if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                    //左半边是[l,mid-1),右半边是[mid+1,r]
                    if ((mid - 1 - l) % 2 != 0) {
                        r = mid - 2;
                    } else {
                        l = mid + 1;
                    }
                } else {
                    //左半边是[l,mid),右半边是[mid+2,r)
                    if ((mid - l) % 2 != 0) {
                        r = mid - 1;
                    } else {
                        l = mid + 2;
                    }
                }
            }
        }
        return nums[l];
    }
}
