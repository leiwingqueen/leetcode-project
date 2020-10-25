package com.liyongquan.array;

import java.util.*;

/**
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * <p>
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * <p>
 * 以数组形式返回答案。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * 示例 2：
 * <p>
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * 示例 3：
 * <p>
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SmallerNumbers {
    /**
     * 暴力解法
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[j] < nums[i]) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }

    /**
     * 先排序
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        //记录对应数字的位置
        Map<Integer, List<Integer>> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            List<Integer> pos = map.getOrDefault(nums[i], new LinkedList<>());
            pos.add(i);
        }
        //排序
        Arrays.sort(nums);
        //TODO
        return null;
    }

}
