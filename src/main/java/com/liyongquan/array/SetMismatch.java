package com.liyongquan.array;

/**
 * 集合 S 包含从1到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个元素复制了成了集合里面的另外一个元素的值，导致集合丢失了一个整数并且有一个元素重复。
 * <p>
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。你的任务是首先寻找到重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,2,4]
 * 输出: [2,3]
 * 注意:
 * <p>
 * 给定数组的长度范围是 [2, 10000]。
 * 给定的数组是无序的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/set-mismatch
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SetMismatch {
    /**
     * bitmap解法
     * 注意这里返回多第一个必须为重复多数字，第二个为缺失的数字
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int[] bitmap = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            bitmap[nums[i] - 1]++;
        }
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (bitmap[i] == 2) {
                result[0] = i + 1;
            } else if (bitmap[i] == 0) {
                result[1] = i + 1;
            }
        }
        return result;
    }
}
