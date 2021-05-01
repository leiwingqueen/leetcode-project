package com.liyongquan.interview;

import java.util.*;

/**
 * 前几天被问到一道字节的面试题：
 * input: 9,8,7,3,4,2,1
 * output: 9,8,7,2,1
 * <p>
 * input: 3,3,1
 * output: 1
 * <p>
 * 找到数组中, 比左边所有数字都小, 比右边所有数字都大的 数字
 * <p>
 * 先从左到右遍历，求一个leftMin数组，记录nums[i]左边的最小值；
 * 再从右到左遍历求一个rightMax数组，记录nums[i]右边的最大值。
 * 然后从左到右遍历每个元素，如果某元素满足leftMin[i]>nums[i]>rightMax[i]，则求得该数
 * <p>
 * 作者：wenwen
 * 链接：https://leetcode-cn.com/circle/discuss/rRusa7/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class FindNum {
    /**
     * 求每个数字左边的最小值,每个数字右边的最大值
     * <p>
     * 右边的最大值可以用单调栈来解决
     * <p>
     * 时间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public List<Integer> find(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return Collections.emptyList();
        }
        if (len == 1) {
            return Arrays.asList(nums[0]);
        }
        //先初始化右边的单调栈(递减栈，栈顶就是最小的元素)
        Deque<Integer> deque = new LinkedList<>();
        for (int num : nums) {
            while (!deque.isEmpty() && deque.peekFirst() < num) {
                deque.pollFirst();
            }
            deque.offerFirst(num);
        }
        List<Integer> res = new ArrayList<>();
        int leftMin = Integer.MAX_VALUE;
        for (int num : nums) {
            //更新单调栈
            int rightMax = Integer.MIN_VALUE;
            if (!deque.isEmpty()) {
                if (num == deque.peekLast()) {
                    deque.pollLast();
                }
                if (!deque.isEmpty()) {
                    rightMax = deque.peekLast();
                }
            }
            if (num < leftMin && num > rightMax) {
                res.add(num);
            }
            //更新左边最小值
            leftMin = Math.min(num, leftMin);
        }
        return res;
    }
}
