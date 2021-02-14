package com.liyongquan.array;

/**
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 注意：
 * <p>
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-consecutive-ones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, current = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                current++;
            } else {
                if (current > max) {
                    max = current;
                }
                current = 0;
            }
        }
        return current > max ? current : max;
    }

    /**
     * 双指针的写法
     *
     * @param nums
     * @return
     */
    public int findMaxConsecutiveOnes2(int[] nums) {
        int res = 0;
        int l = 0, r = 0;
        while (r < nums.length) {
            if (nums[r] == 1) {
                r++;
                res = Math.max(r - l, res);
            } else {
                r++;
                l = r;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        MaxConsecutiveOnes ones = new MaxConsecutiveOnes();
        int maxConsecutiveOnes = ones.findMaxConsecutiveOnes(new int[]{1, 1, 1, 1, 1, 1});
        System.out.println(maxConsecutiveOnes);
    }
}
