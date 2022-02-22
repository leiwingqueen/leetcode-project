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

    /**
     * 超时
     *
     * @param nums
     * @return
     */
    public int numberOfGoodSubsets(int[] nums) {
        int mod = 1_000_000_007;
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
        //从前N个数字选，并且集合为k的dp的合法数量(背包问题，最后一个数字选和不选)
        //f(n,k)=f(n-1,k)+f(n-1,k-a[n-1])
        //int[][] dp = new int[list.size() + 1][mx];
        int[] dp = new int[mx];
        int[] ndp = new int[mx];
        dp[0] = 1;
        for (int i = 1; i <= list.size(); i++) {
            Integer mask = list.get(i - 1);
            for (int j = 0; j < mx; j++) {
                ndp[j] = dp[j];
                if ((j & mask) == mask) {
                    ndp[j] = (ndp[j] + dp[j - mask]) % mod;
                }
            }
            //替换数组
            System.arraycopy(ndp, 0, dp, 0, mx);
            //dp = ndp;
        }
        int cnt = 0;
        //排除掉0的场景，不能算一个合法解
        for (int i = 1; i < mx; i++) {
            cnt = (cnt + dp[i]) % mod;
        }
        //对1的情况单独计算(多一个1的话会让解增加1倍，选和不选两种选项)，为了避免溢出只能一个个算了
        //等价于cnt<<oneCnt
        for (int i = 0; i < oneCnt; i++) {
            cnt = (cnt << 1) % mod;
        }
        return cnt;
    }

    /**
     * 状态压缩优化
     * <p>
     * 总算击败5%的人
     *
     * @param nums
     * @return
     */
    public int numberOfGoodSubsets2(int[] nums) {
        int mod = 1_000_000_007;
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
        //从前N个数字选，并且集合为k的dp的合法数量(背包问题，最后一个数字选和不选)
        //f(n,k)=f(n-1,k)+f(n-1,k-a[n-1])
        //int[][] dp = new int[list.size() + 1][mx];
        int[] dp = new int[mx];
        dp[0] = 1;
        for (int i = 1; i <= list.size(); i++) {
            Integer mask = list.get(i - 1);
            //反过来遍历，因为状态j只会由上一层的[0,j]的状态决定，因此从后遍历可以减少空间使用
            for (int j = mx - 1; j >= 0; j--) {
                if ((j & mask) == mask) {
                    dp[j] = (dp[j] + dp[j - mask]) % mod;
                }
            }
        }
        int cnt = 0;
        //排除掉0的场景，不能算一个合法解
        for (int i = 1; i < mx; i++) {
            cnt = (cnt + dp[i]) % mod;
        }
        //对1的情况单独计算(多一个1的话会让解增加1倍，选和不选两种选项)，为了避免溢出只能一个个算了
        //等价于cnt<<oneCnt
        for (int i = 0; i < oneCnt; i++) {
            cnt = (cnt << 1) % mod;
        }
        return cnt;
    }

    /**
     * 继续优化，由于数字可能的范围很小，因此数字重复的概率很高，相同的数字我们可以归并到一种场景下
     *
     * @param nums
     * @return
     */
    public int numberOfGoodSubsets3(int[] nums) {
        int mod = 1_000_000_007;
        int nmx = 30;
        //统计出现频率
        int[] freq = new int[nmx + 1];
        for (int num : nums) {
            freq[num]++;
        }
        //过滤掉其他不合法数字和预生成对应的mask
        int[] masks = new int[nmx + 1];
        for (int i = 2; i <= nmx; i++) {
            int mask = split(i);
            if (mask < 0) {
                freq[i] = 0;
            } else {
                masks[i] = mask;
            }
        }
        //状态压缩dp
        //也就1024种情况
        int mx = 1 << PRIME.length;
        //从前N个数字选，并且集合为k的dp的合法数量(背包问题，最后一个数字选和不选)
        //f(n,k)=f(n-1,k)+f(n-1,k-a[n-1])
        //int[][] dp = new int[list.size() + 1][mx];
        int[] dp = new int[mx];
        dp[0] = 1;
        for (int i = 2; i <= nmx; i++) {
            if (freq[i] > 0) {
                int mask = masks[i];
                //反过来遍历，因为状态j只会由上一层的[0,j]的状态决定，因此从后遍历可以减少空间使用
                for (int j = mx - 1; j >= 0; j--) {
                    if ((j & mask) == mask) {
                        dp[j] = (int) ((dp[j] + (long) dp[j - mask] * freq[i]) % mod);
                    }
                }
            }
        }
        int cnt = 0;
        //排除掉0的场景，不能算一个合法解
        for (int i = 1; i < mx; i++) {
            cnt = (cnt + dp[i]) % mod;
        }
        //对1的情况单独计算(多一个1的话会让解增加1倍，选和不选两种选项)，为了避免溢出只能一个个算了
        //等价于cnt<<oneCnt
        for (int i = 0; i < freq[1]; i++) {
            cnt = (cnt << 1) % mod;
        }
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
