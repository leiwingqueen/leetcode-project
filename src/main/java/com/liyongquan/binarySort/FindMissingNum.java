package com.liyongquan.binarySort;

/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [0,1,3]
 * 输出: 2
 *
 * 示例 2:
 *
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 *
 *
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMissingNum {
    /**
     * 如果nums[i]!=i 且 nums[i-1]==i-1，这个就是我们要找到的数字。
     * 可以使用二分查找解决。复杂度O(log(n))
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int left=0;
        int right=nums.length-1;
        while (left<=right){
            if (left==right) {
                if (nums[left]!=left&&(left<=0||nums[left-1]==left-1)){
                    return left;
                }
                return nums.length;
            }
            int middle=(left+right)>>1;
            if (nums[middle]!=middle&&(middle<=0||nums[middle-1]==middle-1)) {
                return middle;
            }else if(nums[middle]==middle){
                left=middle+1;
            }else{
                right=middle-1;
            }
        }
        return nums.length;
    }
}
