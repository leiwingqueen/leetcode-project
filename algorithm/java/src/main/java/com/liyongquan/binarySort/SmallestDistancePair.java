package com.liyongquan.binarySort;

//719. 找出第 k 小的距离对
//给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
//
//示例 1:
//
//输入：
//nums = [1,3,1]
//k = 1
//输出：0
//解释：
//所有数对如下：
//(1,3) -> 2
//(1,1) -> 0
//(3,1) -> 2
//因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
//提示:
//
//2 <= len(nums) <= 10000.
//0 <= nums[i] < 1000000.
//1 <= k <= len(nums) * (len(nums) - 1) / 2.
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-k-th-smallest-pair-distance
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.Arrays;

/**
 * 字节面试题
 *
 * @author liyongquan
 * @date 2021/12/15
 */
public class SmallestDistancePair {
    /**
     * 二分+滑窗
     *
     * 不容易啊，写了两个小时
     *
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        //距离的区间范围[0,max]
        int max = nums[len - 1] - nums[0];
        //二分查找，找右边界
        int l = 0, r = max;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int[] find = findSmaller(nums, mid);
            if (find[0] >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    //滑窗统计距离<=dis的数量和最大值
    public int[] findSmaller(int[] nums, int dis) {
        int l = 0, r = 0;
        int cnt = 0;
        int max = 0;
        while (r < nums.length) {
            if (nums[r] - nums[l] <= dis) {
                cnt += r - l;
                max = Math.max(nums[r] - nums[l], max);
                r++;
            } else {
                //cnt += (r - l + 1) * (r - l) / 2;
                l++;
            }
        }
        return new int[]{cnt, max};
    }
}
