package com.liyongquan.twopointer;

/**
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {
    /**
     * 暴力解法
     * 没有利用递增的特性，时间复杂度O(n*(n-1)/2)
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[]{};
    }

    /**
     * 双指针解法。如何证明该解法的正确性？
     *
     * 对于数组A，假设左右边界的index为i,j
     * 假设A[i]+A[j]>target，则我们可以认为(A[i],A[i+1])...(A[i],A[j-1])这些组合必然不满足需求(数组递增)，相当于右边界=j的所有可能的集合被消除了。
     * 我们选择左边界i+1
     *
     * 这里有朋友会问题，为什么不能考虑j+1？
     * 因为右边界是从右向左移动，如果目前的右边界为j，则可以认为j+1的场景已经不可能满足目标
     *
     * 左边界类似的问题
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }
}
