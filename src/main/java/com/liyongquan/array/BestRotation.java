package com.liyongquan.array;

//给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，这样可以使数组变为 [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。此后，任何值小于或等于其索引的项都可以记作一分。
//
//例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
//在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
//
// 
//
//示例 1：
//
//输入：nums = [2,3,1,4,0]
//输出：3
//解释：
//下面列出了每个 k 的得分：
//k = 0,  nums = [2,3,1,4,0],    score 2
//k = 1,  nums = [3,1,4,0,2],    score 3
//k = 2,  nums = [1,4,0,2,3],    score 3
//k = 3,  nums = [4,0,2,3,1],    score 4
//k = 4,  nums = [0,2,3,1,4],    score 3
//所以我们应当选择 k = 3，得分最高。
//示例 2：
//
//输入：nums = [1,3,0,2,4]
//输出：0
//解释：
//nums 无论怎么变化总是有 3 分。
//所以我们将选择最小的 k，即 0。
// 
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] < nums.length
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/smallest-rotation-with-highest-score
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.HashMap;
import java.util.Map;

public class BestRotation {
    /**
     * 暴力解法
     *
     * @param nums
     * @return
     */
    public int bestRotation(int[] nums) {
        int len = nums.length;
        int max = 0;
        int res = 0;
        for (int k = 0; k < len; k++) {
            int sum = 0;
            for (int i = 0; i < len; i++) {
                //新的下标
                int idx = (i + len - k) % len;
                if (nums[i] <= idx) {
                    sum++;
                }
            }
            if (sum > max) {
                max = sum;
                res = k;
            }
        }
        return res;
    }

    /**
     * 看题解的时候发现的解法
     *
     * @param nums
     * @return
     */
    public int bestRotation2(int[] nums) {
        int len = nums.length;
        int[] arr = new int[len];
        //统计每个数字出现的次数
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < len; i++) {
            arr[i] = i - nums[i];
            cnt.put(arr[i], cnt.getOrDefault(arr[i], 0) + 1);
        }
        int max = 0;
        int res = 0;
        //计算初始状态的值
        for (int i = 0; i < len; i++) {
            if (arr[i] >= 0) {
                max++;
            }
        }
        //轮转迭代
        int cur = max;
        for (int k = 1; k < len; k++) {
            cur = cur - cnt.getOrDefault(k - 1, 0) + 1;
            int v = arr[k - 1] + len - 1;
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
            if (cur > max) {
                max = cur;
                res = k;
            }
        }
        return res;
    }
}