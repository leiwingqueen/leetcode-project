package com.liyongquan.math;

//好子集的数量
//给你一个整数数组 nums 。如果 nums 的一个子集中，所有元素的乘积可以表示为一个或多个 互不相同的质数 的乘积，那么我们称它为 好子集 。
//
//比方说，如果 nums = [1, 2, 3, 4] ：
//[2, 3] ，[1, 2, 3] 和 [1, 3] 是 好 子集，乘积分别为 6 = 2*3 ，6 = 2*3 和 3 = 3 。
//[1, 4] 和 [4] 不是 好 子集，因为乘积分别为 4 = 2*2 和 4 = 2*2 。
//请你返回 nums 中不同的 好 子集的数目对 109 + 7 取余 的结果。
//
//nums 中的 子集 是通过删除 nums 中一些（可能一个都不删除，也可能全部都删除）元素后剩余元素组成的数组。如果两个子集删除的下标不同，那么它们被视为不同的子集。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3,4]
//输出：6
//解释：好子集为：
//- [1,2]：乘积为 2 ，可以表示为质数 2 的乘积。
//- [1,2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
//- [1,3]：乘积为 3 ，可以表示为质数 3 的乘积。
//- [2]：乘积为 2 ，可以表示为质数 2 的乘积。
//- [2,3]：乘积为 6 ，可以表示为互不相同的质数 2 和 3 的乘积。
//- [3]：乘积为 3 ，可以表示为质数 3 的乘积。
//示例 2：
//
//输入：nums = [4,2,3,15]
//输出：5
//解释：好子集为：
//- [2]：乘积为 2 ，可以表示为质数 2 的乘积。
//- [2,3]：乘积为 6 ，可以表示为互不相同质数 2 和 3 的乘积。
//- [2,15]：乘积为 30 ，可以表示为互不相同质数 2，3 和 5 的乘积。
//- [3]：乘积为 3 ，可以表示为质数 3 的乘积。
//- [15]：乘积为 15 ，可以表示为互不相同质数 3 和 5 的乘积。
// 
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 30
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/the-number-of-good-subsets
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.ArrayList;
import java.util.List;

public class NumberOfGoodSubsets {
    public static final int[] PRIME = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

    public int numberOfGoodSubsets(int[] nums) {
        //1的数量
        int oneCnt = 0;
        //过滤1和其他不合法数字
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            if (num == 1) {
                oneCnt++;
            } else {
                int mask = split(num);
                if (mask > 0) {
                    list.add(mask);
                }
            }
        }
        //状态压缩dp
        //也就1024种情况
        int mx = 1 << PRIME.length;
        //前N个数字选中，并且集合为k的dp的合法数量（第N个数字必须选中）
        int[][] dp = new int[list.size() + 1][mx];
        for (int i = 0; i <= list.size(); i++) {
            dp[i][0] = 1;
        }
        int cnt = 0;
        for (int i = 1; i <= list.size(); i++) {
            Integer mask = list.get(i - 1);
            for (int j = 1; j < mx; j++) {
                if ((j & mask) == mask) {
                    int sum = 0;
                    //计算一列的和
                    for (int k = 0; k < i; k++) {
                        sum += dp[k][j - mask];
                    }
                    dp[i][j] = sum;
                    cnt += dp[i][j];
                }
            }
        }
        //对1的情况单独计算
        cnt *= (1 << oneCnt);
        return cnt;
    }

    //因素分解，用bit表示，简化后面的计算交集的运算
    private int split(int num) {
        int mask = 0;
        for (int i = 0; i < PRIME.length; i++) {
            int p = PRIME[i];
            int cnt = 0;
            while (num % p == 0) {
                cnt += 1;
                num /= p;
            }
            if (cnt > 1) {
                return -1;
            } else if (cnt == 1) {
                mask |= 1 << i;
            }
        }
        return mask;
    }
}
