package com.liyongquan.array;

/**
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 *  
 * <p>
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * <p>
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ProductExceptSelf {
    /**
     * 分别维护两个数组统计左右两侧的乘积
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length], right = new int[nums.length];
        int p = 1;
        for (int i = 0; i < nums.length; i++) {
            left[i] = p;
            p *= nums[i];
        }
        p = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            right[i] = p;
            p *= nums[i];
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = left[i] * right[i];
        }
        return result;
    }


    /**
     * left和right有没可能统一维护一个数组呢？
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(1)【不包括返回的结构数组】
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int[] result = new int[nums.length];
        int p = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = p;
            p *= nums[i];
        }
        p = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= p;
            p *= nums[i];
        }
        return result;
    }

}
