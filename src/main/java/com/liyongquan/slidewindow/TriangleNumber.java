package com.liyongquan.slidewindow;

//给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
//
// 示例 1:
//
//
//输入: [2,2,3,4]
//输出: 3
//解释:
//有效的组合是:
//2,3,4 (使用第一个 2)
//2,3,4 (使用第二个 2)
//2,2,3
//
//
// 注意:
//
//
// 数组长度不超过1000。
// 数组里整数的范围为 [0, 1000]。
//
// Related Topics 贪心 数组 双指针 二分查找 排序
// 👍 217 👎 0

import java.util.Arrays;

/**
 * @author liyongquan
 * @date 2021/8/4
 */
public class TriangleNumber {
    /**
     * 时间复杂度O(n^2)
     *
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int p1 = 0, p2 = p1 + 1, p3 = p2 + 1;
        int cnt = 0;
        while (p3 < len) {
            //滑动窗口，这里的时间复杂度是O(n)
            while (p3 < len) {
                //找到p3的右边界
                while (p3 <= len - 1 && nums[p1] + nums[p2] > nums[p3]) {
                    p3++;
                }
                cnt += p3 - p2 - 1;
                p2++;
                if (p3 <= p2) {
                    p3 = p2 + 1;
                }
                if (p3 > p2 + 1 && p3 == len && nums[p1] + nums[p2] > nums[p3 - 1]) {
                    cnt += p3 - p2 - 1;
                }
            }
            p1++;
            p2 = p1 + 1;
            p3 = p2 + 1;
        }
        return cnt;
    }

    /**
     * 暴力解法
     *
     * @param nums
     * @return
     */
    public int triangleNumber2(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < len - 2; i++) {
            //稍微优化了一点点，当j向后移动的时候，k不需要重新从j+1开始扫描
            int k = i + 2;
            for (int j = i + 1; j < len - 1; j++) {
                if (k <= j) {
                    k = j + 1;
                }
                while (k < len && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                res += k - j - 1;
            }
        }
        return res;
    }
}
