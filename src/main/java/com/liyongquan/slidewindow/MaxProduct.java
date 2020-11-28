package com.liyongquan.slidewindow;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProduct {
    /**
     * dp解法
     * <p>
     * f0(i)[0]为前i个数字中选择i的时候的最大值
     * f0(i)[1]为选择i的时候的最小值
     * <p>
     * 则有
     * f0(i)[0]=max{f0(i-1)[0]*nums[i],nums[i],f0(i-1)[1]*nums[i]}
     * f0(i)[1]=min{f0(i-1)[0]*nums[i],nums[i],f0(i-1)[1]*nums[i]}
     * <p>
     * f1(i)[0]为前i个数字不选择i的时候的最大值
     * <p>
     * 同理有
     * f1(i)[0]=max{f0(i-1)[0],f1(i-1)[0]}
     * f1(i)[1]=min{f0(i-1)[1],f1(i-1)[1]}
     * <p>
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        //初始化
        int[] f0 = new int[2], f1 = new int[2];
        f0[0] = nums[0];
        f0[1] = nums[0];
        f1[0] = 1;
        f1[1] = 1;
        //dp迭代
        for (int i = 1; i < nums.length; i++) {
            int f00 = Math.max(f0[0] * nums[i], Math.max(nums[i], f0[1] * nums[i]));
            int f01 = Math.max(f0[0] * nums[i], Math.min(nums[i], f0[1] * nums[i]));
            //必须选一个，所以当i=1的时候这里需要做特殊处理
            int f10 = i == 1 ? nums[0] : Math.max(f0[0], f1[0]);
            int f11 = i == 1 ? nums[0] : Math.min(f0[1], f1[1]);
            f0[0] = f00;
            f0[1] = f01;
            f1[0] = f10;
            f1[1] = f11;
        }
        return Math.max(f0[0], f1[0]);
    }
}
