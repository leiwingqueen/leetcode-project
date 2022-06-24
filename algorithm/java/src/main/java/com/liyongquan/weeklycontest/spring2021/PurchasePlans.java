package com.liyongquan.weeklycontest.spring2021;

import java.util.Arrays;

/**
 * LCP 28. 采购方案
 * <p>
 * 小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，请问他有多少种采购方案。
 * <p>
 * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,5,3,5], target = 6
 * <p>
 * 输出：1
 * <p>
 * 解释：预算内仅能购买 nums[0] 与 nums[2]。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,1,9], target = 10
 * <p>
 * 输出：4
 * <p>
 * 解释：符合预算的采购方案如下：
 * nums[0] + nums[1] = 4
 * nums[0] + nums[2] = 3
 * nums[1] + nums[2] = 3
 * nums[2] + nums[3] = 10
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 10^5
 * 1 <= nums[i], target <= 10^5
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4xy4Wx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PurchasePlans {
    /**
     * 时间复杂度O(n^2)
     *
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans(int[] nums, int target) {
        int len = nums.length;
        int fn = nums[0] + nums[1] <= target ? 1 : 0;
        for (int i = 2; i < len; i++) {
            int cnt = 0;
            if (nums[i] < target) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] <= target - nums[i]) {
                        cnt++;
                    }
                }
                cnt %= 1000000007;
            }
            fn = (fn + cnt) % 1000000007;
        }
        return fn;
    }

    /**
     * 稍微优化后勉强能过
     *
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans2(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int fn = nums[0] + nums[1] <= target ? 1 : 0;
        for (int i = 2; i < len; i++) {
            if (nums[i] < target) {
                int j = i - 1;
                while (j >= 0 && nums[j] > target - nums[i]) {
                    j--;
                }
                fn = (fn + j + 1) % 1000000007;
            }
        }
        return fn;
    }

    /**
     * 可以使用二分查找加速搜索过程
     * <p>
     * 时间复杂度O(nlog(n))
     *
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans3(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int fn = nums[0] + nums[1] <= target ? 1 : 0;
        for (int i = 2; i < len; i++) {
            if (nums[i] < target) {
                //二分查找右边界
                int l = 0, r = i - 1;
                while (l < r) {
                    int middle = (l + r) / 2;
                    if (nums[middle] > target - nums[i]) {
                        r = middle - 1;
                    } else {
                        if (nums[middle + 1] <= target - nums[i]) {
                            l = middle + 1;
                        } else {
                            l = middle;
                            break;
                        }
                    }
                }
                if (nums[l] <= target - nums[i]) {
                    fn = (fn + l + 1) % 1000000007;
                }
            }
        }
        return fn;
    }

    /**
     * 双指针解法
     * <p>
     * 左指针l=0,r=n-1
     * 我们需要统计每个左指针的最大右边界。
     * <p>
     * 假设每次向右移动左指针，它的右边界必然在上次的左边界左侧。
     * [l0,r0]
     * [l1,r1]
     * l1>l0,则r1<=r0
     *
     * @param nums
     * @param target
     * @return
     */
    public int purchasePlans4(int[] nums, int target) {
        int mod = 1000000007;
        int len = nums.length;
        Arrays.sort(nums);
        int l = 0, r = len - 1;
        int cnt = 0;
        while (l < r) {
            //找到右边界
            while (l < r && nums[l] + nums[r] > target) {
                r--;
            }
            cnt += (r - l);
            cnt %= mod;
            l++;
        }
        return cnt % mod;
    }
}
