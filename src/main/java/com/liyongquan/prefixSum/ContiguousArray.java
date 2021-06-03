package com.liyongquan.prefixSum;

import com.sun.deploy.uitoolkit.impl.fx.FXPreloader;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 * 示例 2:
 * <p>
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contiguous-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ContiguousArray {
    /**
     * 假设f0(j),f1(j)分别是[0,j)的0，1的数量
     * <p>
     * [i,j)的0,1数量为
     * f0(j)-f0(i),f1(j)-f1(i)
     * 假设0的数量等于1的数量，我们有
     * f0(j)-f0(i)=f1(j)-f1(i)
     * <p>
     * f0(j)-f1(j)=f0(i)-f1(i)
     * <p>
     * 假设g(j)是[0,j)上的0的数量-1的数量
     * g(j)=g(i)
     *
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        int len = nums.length;
        //计算前缀和
        int[] pre = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            pre[i] = pre[i - 1] + (nums[i - 1] == 0 ? 1 : -1);
        }
        //记录前缀和的左下标
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int max = 0;
        for (int i = 1; i <= len; i++) {
            if (map.containsKey(pre[i])) {
                max = Math.max(max, i - map.get(pre[i]));
            } else {
                map.put(pre[i], i);
            }
        }
        return max;
    }

    /**
     * 在上面基础优化一下
     * <p>
     * 减少前缀和的存储
     * <p>
     * 奇怪，这种解法比上面的还要慢一些
     *
     * @param nums
     * @return
     */
    public int findMaxLength2(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int sub = 0;
        int max = 0;
        for (int i = 1; i <= len; i++) {
            sub += nums[i - 1] == 0 ? 1 : -1;
            if (map.containsKey(sub)) {
                max = Math.max(max, i - map.get(sub));
            } else {
                map.put(sub, i);
            }
        }
        return max;
    }
}
