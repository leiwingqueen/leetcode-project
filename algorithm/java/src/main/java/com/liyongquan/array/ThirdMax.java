package com.liyongquan.array;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3, 2, 1]
 * <p>
 * 输出: 1
 * <p>
 * 解释: 第三大的数是 1.
 * 示例 2:
 * <p>
 * 输入: [1, 2]
 * <p>
 * 输出: 2
 * <p>
 * 解释: 第三大的数不存在, 所以返回最大的数 2 .
 * 示例 3:
 * <p>
 * 输入: [2, 2, 3, 1]
 * <p>
 * 输出: 1
 * <p>
 * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
 * 存在两个值为2的数，它们都排第二。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/third-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ThirdMax {
    public int thirdMax(int[] nums) {
        //大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((i1, i2) -> i1 - i2);
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            if (queue.size() > 3) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    /**
     * 维护3个变量
     *
     * @param nums
     * @return
     */
    public int thirdMax2(int[] nums) {
        long m1, m2, m3;
        m1 = m2 = m3 = Long.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == m1 || nums[i] == m2 || nums[i] == m3) {
                continue;
            }
            if (nums[i] > m1) {
                m3 = m2;
                m2 = m1;
                m1 = nums[i];
            } else if (nums[i] > m2) {
                m3 = m2;
                m2 = nums[i];
            } else if (nums[i] > m3) {
                m3 = nums[i];
            }
        }
        return m3 < Integer.MIN_VALUE ? (int) m1 : (int) m3;
    }

    public static void main(String[] args) {
        ThirdMax thirdMax = new ThirdMax();
        int i = thirdMax.thirdMax2(new int[]{2, 2, 3, 1});
        System.out.println(i);
    }
}
