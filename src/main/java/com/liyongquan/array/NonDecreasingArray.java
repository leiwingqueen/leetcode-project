package com.liyongquan.array;

/**
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * <p>
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 * <p>
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *  
 * <p>
 * 说明：
 * <p>
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-decreasing-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NonDecreasingArray {
    /**
     * 错误的解法，下面的解法才是正确的
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (count >= 1) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    /**
     * 拐点的两种处理方式
     * 假设A[i-1],A[i],A[i+1]为连续3个元素，i为拐点。对于拐点有两种处理方式
     * 1.当A[i-1]<=A[i+1]
     * 拐点下移，A[i]=A[i+1]
     * 2.当A[i-1]>A[i+1]
     * 拐点的下一个节点上移，A[i+1]=A[i]
     *
     * @param nums
     * @return
     */
    public boolean checkPossibility2(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (count >= 1) {
                    return false;
                }
                if (i == 0 || nums[i - 1] <= nums[i + 1]) {
                    nums[i] = nums[i + 1];
                } else {
                    nums[i + 1] = nums[i];
                }
                count++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NonDecreasingArray array = new NonDecreasingArray();
        boolean b = array.checkPossibility2(new int[]{4, 2, 3});
        System.out.println(b);
        b = array.checkPossibility2(new int[]{4, 2, 1});
        System.out.println(b);
        b = array.checkPossibility2(new int[]{3, 4, 2, 3});
        System.out.println(b);
    }
}
