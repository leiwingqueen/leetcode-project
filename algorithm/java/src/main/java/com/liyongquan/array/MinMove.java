package com.liyongquan.array;

/**
 * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动将会使 n - 1 个元素增加 1。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [1,2,3]
 * <p>
 * 输出:
 * 3
 * <p>
 * 解释:
 * 只需要3次移动（注意每次移动会增加两个元素的值）：
 * <p>
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinMove {
    /**
     * 每次增加除最大数之外的n-1个数字，但是这种解法会超时
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        //求最大值
        int index = 0;
        int max = nums[0];
        boolean equal = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                index = i;
                equal = false;
            } else if (nums[i] < max) {
                equal = false;
            }
        }
        if (equal) {
            return 0;
        }
        //每次移动除最大数以外的所有数字
        int count = 0;
        while (true) {
            int index1 = index;
            equal = true;
            for (int i = 0; i < nums.length; i++) {
                if (i != index) {
                    nums[i]++;
                    if (nums[i] > max) {
                        max = nums[i];
                        index1 = i;
                        equal = false;
                    } else if (nums[i] < max) {
                        equal = false;
                    }
                }
            }
            count++;
            index = index1;
            if (equal) {
                return count;
            }
        }
    }

    public int minMoves2(int[] nums) {
        int sum = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            min = Math.min(min, nums[i]);
        }
        return sum - min * nums.length;
    }
}
