package com.liyongquan.array;

import com.liyongquan.linklist.AddTwoNumber;

import java.util.HashSet;
import java.util.Set;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 *
 * 示例 2：
 *
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 *
 *
 *
 * 限制：
 *
 *     1 <= nums.length <= 10^5
 *     1 <= nums[i] <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SumNum {
    public int[] twoSum2(int[] nums, int target) {
        Set<Integer> s=new HashSet<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            s.add(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (s.contains(target-nums[i])) {
                return new int[]{nums[i],target-nums[i]};
            }
        }
        return new int[0];
    }

    /**
     * 采用双指针，才是这道题的真正解法
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int i=0;
        int j=nums.length-1;
        while (i<j){
            if (nums[i]+nums[j]>target) {
                j--;
            }else if(nums[i]+nums[j]<target){
                i++;
            }else{
                return new int[]{nums[i],nums[j]};
            }
        }
        return new int[0];
    }
}
