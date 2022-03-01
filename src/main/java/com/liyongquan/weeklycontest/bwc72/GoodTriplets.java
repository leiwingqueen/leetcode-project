package com.liyongquan.weeklycontest.bwc72;

//给你两个下标从 0 开始且长度为 n 的整数数组 nums1 和 nums2 ，两者都是 [0, 1, ..., n - 1] 的 排列 。
//
//好三元组 指的是 3 个 互不相同 的值，且它们在数组 nums1 和 nums2 中出现顺序保持一致。换句话说，如果我们将 pos1v 记为值 v 在 nums1 中出现的位置，pos2v 为值 v 在 nums2 中的位置，那么一个好三元组定义为 0 <= x, y, z <= n - 1 ，且 pos1x < pos1y < pos1z 和 pos2x < pos2y < pos2z 都成立的 (x, y, z) 。
//
//请你返回好三元组的 总数目 。
//
// 
//
//示例 1：
//
//输入：nums1 = [2,0,1,3], nums2 = [0,1,2,3]
//输出：1
//解释：
//总共有 4 个三元组 (x,y,z) 满足 pos1x < pos1y < pos1z ，分别是 (2,0,1) ，(2,0,3) ，(2,1,3) 和 (0,1,3) 。
//这些三元组中，只有 (0,1,3) 满足 pos2x < pos2y < pos2z 。所以只有 1 个好三元组。
//示例 2：
//
//输入：nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
//输出：4
//解释：总共有 4 个好三元组 (4,0,3) ，(4,0,2) ，(4,1,3) 和 (4,1,2) 。
// 
//
//提示：
//
//n == nums1.length == nums2.length
//3 <= n <= 105
//0 <= nums1[i], nums2[i] <= n - 1
//nums1 和 nums2 是 [0, 1, ..., n - 1] 的排列。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-good-triplets-in-an-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class GoodTriplets {
    /**
     * 先来一个能想到的解法
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        //生成映射关系
        int[] mp = new int[n];
        for (int i = 0; i < n; i++) {
            mp[nums2[i]] = i;
        }
        //下标是nums1对应的下标，value是对应nums2的下标
        int[] mp2 = new int[n];
        for (int i = 0; i < n; i++) {
            mp2[i] = mp[nums1[i]];
        }
        //满足条件的2元组
        int[] dp2 = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (mp2[i] > mp2[j]) {
                    dp2[i]++;
                }
            }
        }
        //满足条件的3元祖
        long sum = 0;
        for (int i = 2; i < n; i++) {
            for (int j = 1; j < i; j++) {
                if (mp2[i] > mp2[j]) {
                    sum += dp2[j];
                }
            }
        }
        return sum;
    }
}
