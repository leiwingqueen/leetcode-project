package com.liyongquan.math;

//给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
//
// 
//
//进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
//
// 
//
//示例 1：
//
//输入：nums = [1,2,1,3,2,5]
//输出：[3,5]
//解释：[5, 3] 也是有效的答案。
//示例 2：
//
//输入：nums = [-1,0]
//输出：[-1,0]
//示例 3：
//
//输入：nums = [0,1]
//输出：[1,0]
//提示：
//
//2 <= nums.length <= 3 * 104
//-231 <= nums[i] <= 231 - 1
//除两个只出现一次的整数外，nums 中的其他数字都出现两次
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/single-number-iii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/10/30
 */
public class SingleNumberIII {
    /**
     * 排序O(nlogn)
     *
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        Arrays.sort(nums);
        List<Integer> res = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                cnt++;
            } else {
                if (nums[i] == nums[i - 1]) {
                    cnt++;
                } else {
                    if (cnt == 1) {
                        res.add(nums[i - 1]);
                    }
                    cnt = 1;
                }
            }
        }
        if (cnt == 1) {
            res.add(nums[nums.length - 1]);
        }
        return new int[]{res.get(0), res.get(1)};
    }

    /**
     * hashmap
     *
     * @param nums
     * @return
     */
    public int[] singleNumber2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) == 2) {
                map.remove(num);
            }
        }
        int[] res = new int[map.size()];
        int idx = 0;
        for (Integer num : map.keySet()) {
            res[idx++] = num;
        }
        return res;
    }

    /**
     * 异或的特性
     *
     * @param nums
     * @return
     */
    public int[] singleNumber3(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        //找出第一位为1的数字
        int bit = 1;
        while ((xor & bit) == 0) {
            bit <<= 1;
        }
        //数字分类
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & bit) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        return new int[]{num1, num2};
    }
}
