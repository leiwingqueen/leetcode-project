package com.liyongquan.greedy;

//1005. K 次取反后最大化的数组和
//给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
//
//选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
//重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
//
//以这种方式修改数组后，返回数组 可能的最大和 。
//
// 
//
//示例 1：
//
//输入：nums = [4,2,3], k = 1
//输出：5
//解释：选择下标 1 ，nums 变为 [4,-2,3] 。
//示例 2：
//
//输入：nums = [3,-1,0,2], k = 3
//输出：6
//解释：选择下标 (1, 2, 2) ，nums 变为 [3,1,0,2] 。
//示例 3：
//
//输入：nums = [2,-3,-1,5,-4], k = 2
//输出：13
//解释：选择下标 (1, 4) ，nums 变为 [2,3,-1,5,4] 。
// 
//
//提示：
//
//1 <= nums.length <= 104
//-100 <= nums[i] <= 100
//1 <= k <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximize-sum-of-array-after-k-negations
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.PriorityQueue;

/**
 * @author liyongquan
 * @date 2021/12/4
 */
public class LargestSumAfterKNegations {
    /**
     * 这个贪心其实有点难，看了官解才理解
     *
     * @param nums
     * @param k
     * @return
     */
    public int largestSumAfterKNegations(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }
        //优先把负数部分先置反
        while (k > 0 && pq.peek() < 0) {
            pq.offer(-pq.poll());
            k--;
        }
        if (k == 0 || pq.peek() == 0 || k % 2 == 0) {
            return sum(pq);
        }
        //剩下的必然全是正数，这时候我们只能把最小的整数一直置换。k是奇数则只需要做一次，偶数则不需要变化
        Integer min = pq.poll();
        return sum(pq) - min;
    }

    private int sum(PriorityQueue<Integer> pq) {
        int sum = 0;
        while (pq.size() > 0) {
            sum += pq.poll();
        }
        return sum;
    }
}
