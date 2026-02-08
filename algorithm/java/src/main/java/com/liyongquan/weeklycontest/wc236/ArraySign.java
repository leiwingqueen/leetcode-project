package com.liyongquan.weeklycontest.wc236;


/**
 * 5726. 数组元素积的符号
 * <p>
 * 已知函数 signFunc(x) 将会根据 x 的正负返回特定值：
 * <p>
 * 如果 x 是正数，返回 1 。
 * 如果 x 是负数，返回 -1 。
 * 如果 x 是等于 0 ，返回 0 。
 * 给你一个整数数组 nums 。令 product 为数组 nums 中所有元素值的乘积。
 * <p>
 * 返回 signFunc(product) 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-1,-2,-3,-4,3,2,1]
 * 输出：1
 * 解释：数组中所有值的乘积是 144 ，且 signFunc(144) = 1
 * 示例 2：
 * <p>
 * 输入：nums = [1,5,0,2,-3]
 * 输出：0
 * 解释：数组中所有值的乘积是 0 ，且 signFunc(0) = 0
 * 示例 3：
 * <p>
 * 输入：nums = [-1,1,-1,1,-1]
 * 输出：-1
 * 解释：数组中所有值的乘积是 -1 ，且 signFunc(-1) = -1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * -100 <= nums[i] <= 100
 */
public class ArraySign {
    public int arraySign(int[] nums) {
        int product = 1;
        for (int num : nums) {
            if (num == 0) {
                product = 0;
                break;
            } else if (num < 0) {
                product = -product;
            }
        }
        return product;
    }
}
