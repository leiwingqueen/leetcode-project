package com.liyongquan.weeklycontest.wc303;

import java.util.HashSet;
import java.util.Set;

//给你一个下标从 0 开始的正整数数组 nums 和一个正整数 k 。
//
//如果满足下述条件，则数对 (num1, num2) 是 优质数对 ：
//
//num1 和 num2 都 在数组 nums 中存在。
//num1 OR num2 和 num1 AND num2 的二进制表示中值为 1 的位数之和大于等于 k ，其中 OR 是按位 或 操作，而 AND 是按位 与 操作。
//返回 不同 优质数对的数目。
//
//如果 a != c 或者 b != d ，则认为 (a, b) 和 (c, d) 是不同的两个数对。例如，(1, 2) 和 (2, 1) 不同。
//
//注意：如果 num1 在数组中至少出现 一次 ，则满足 num1 == num2 的数对 (num1, num2) 也可以是优质数对。
//
//
//
//示例 1：
//
//输入：nums = [1,2,3,1], k = 3
//输出：5
//解释：有如下几个优质数对：
//- (3, 3)：(3 AND 3) 和 (3 OR 3) 的二进制表示都等于 (11) 。值为 1 的位数和等于 2 + 2 = 4 ，大于等于 k = 3 。
//- (2, 3) 和 (3, 2)： (2 AND 3) 的二进制表示等于 (10) ，(2 OR 3) 的二进制表示等于 (11) 。值为 1 的位数和等于 1 + 2 = 3 。
//- (1, 3) 和 (3, 1)： (1 AND 3) 的二进制表示等于 (01) ，(1 OR 3) 的二进制表示等于 (11) 。值为 1 的位数和等于 1 + 2 = 3 。
//所以优质数对的数目是 5 。
//示例 2：
//
//输入：nums = [5,1,1], k = 10
//输出：0
//解释：该数组中不存在优质数对。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//1 <= k <= 60

public class CountExcellentPairs {
    public long countExcellentPairs(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int[] cnt = new int[32];
        for (int num : nums) {
            if (set.add(num)) {
                int c = Integer.bitCount(num);
                cnt[c]++;
            }
        }
        long res = 0;
        for (int i = 0; i < 32; i++) {
            if (cnt[i] == 0) {
                continue;
            }
            int left = k - i;
            if (left < 0) {
                left = 0;
            }
            for (int j = left; j < 32; j++) {
                res += (long) cnt[i] * cnt[j];
            }
        }
        return res;
    }
}
