package com.liyongquan.dp;

/**
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * <p>
 * 要求时间复杂度为O(n)。
 * <p>
 *  
 * <p>
 * 示例1:
 * <p>
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int dp0 = 0;
        int dp1 = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i == 1) {
                dp0 = nums[0];
            } else {
                dp0 = max(dp0, dp1);
            }
            dp1 = max(dp1 + nums[i], nums[i]);
        }
        return max(dp0, dp1);
    }

    /**
     * dp解法
     * f(n)表示最后一个选中的连续数组的最大值
     * p(n)表示最后一个未选中的连续数组的最大值
     * <p>
     * dp方程
     * f(n)=max{f(n-1)+A[n-1],A[n-1]}
     * p(n)=max{f(n-1),p(n-1)}
     * <p>
     * 初始化：
     * f(1)=A[0]
     * p(1)=0
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        if (nums.length<=1) {
            return nums[0];
        }
        int fn = nums[0], pn = 0;
        for (int i = 1; i < nums.length; i++) {
            //至少需要选中一个
            if (i==1) {
                pn=nums[0];
            }else {
                pn = Math.max(fn, pn);
            }
            fn = Math.max(fn + nums[i], nums[i]);
        }
        return Math.max(fn, pn);
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        MaxSubArray maxSubArray = new MaxSubArray();
        //int i = maxSubArray.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        int i = maxSubArray.maxSubArray(new int[]{-1, -2});
        System.out.println(i);
    }
}
