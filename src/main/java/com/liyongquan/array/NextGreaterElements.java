package com.liyongquan.array;

/**
 * 503. 下一个更大元素 II
 * <p>
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NextGreaterElements {
    /**
     * 先暴力解法
     *
     * 时间复杂度O(n^2)
     *
     * 性能击败5%
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new int[]{};
        }
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            //找到下一个更大的数字
            res[i] = -1;
            for (int j = 1; j < len; j++) {
                int idx = (i + j) % len;
                if (nums[idx] > nums[i]) {
                    res[i] = nums[idx];
                    break;
                }
            }
        }
        return res;
    }
}
