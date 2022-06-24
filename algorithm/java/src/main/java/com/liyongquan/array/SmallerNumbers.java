package com.liyongquan.array;

import javafx.util.Pair;

import java.util.Arrays;

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
     * <p>
     * 时间复杂度O(nlogn)，空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        //记录对应数字的位置
        Pair<Integer, Integer>[] list = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            list[i] = new Pair<>(nums[i], i);
        }
        //排序
        Arrays.sort(list, (o1, o2) -> o1.getKey() - o2.getKey());
        int pre = -1;
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (pre == -1 || list[i - 1].getKey() != list[i].getKey()) {
                result[list[i].getValue()] = i;
                pre = i;
            } else {
                result[list[i].getValue()] = pre;
            }
        }
        return result;
    }

    /**
     * 计数器的方式
     * <p>
     * 由于整数的范围是[0,100]，可以直接用bitmap的方式来做一个计数器
     * <p>
     * 时间复杂度O(2*n+100)，空间复杂度O(100)
     *
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent3(int[] nums) {
        int[] bitmap = new int[101];
        //计数器
        for (int i = 0; i < nums.length; i++) {
            bitmap[nums[i]]++;
        }
        //计算每个<=nums[i]的数量(前缀和)
        for (int i = 1; i < bitmap.length; i++) {
            bitmap[i] += bitmap[i - 1];
        }
        //要计算<num的总数，相当于要找bitmap[num-1]的数量
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] == 0 ? 0 : bitmap[nums[i] - 1];
        }
        return result;
    }


    public static void main(String[] args) {
        int[] list = {8, 1, 2, 2, 3};
        SmallerNumbers sn = new SmallerNumbers();
        int[] ints = sn.smallerNumbersThanCurrent2(list);
        for (int anInt : ints) {
            System.out.println(anInt + ",");
        }
    }
}
