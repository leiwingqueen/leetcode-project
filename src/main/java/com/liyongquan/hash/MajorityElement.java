package com.liyongquan.hash;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
//
// 
//
// 
//
//示例 1：
//
//输入：[3,2,3]
//输出：[3]
//示例 2：
//
//输入：nums = [1]
//输出：[1]
//示例 3：
//
//输入：[1,1,1,3,3,2,2,2]
//输出：[1,2]
// 
//
//提示：
//
//1 <= nums.length <= 5 * 104
//-109 <= nums[i] <= 109
// 
//
//进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/majority-element-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/10/22
 */
public class MajorityElement {
    /**
     * 粗暴的hash解法
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new LinkedList<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) == nums.length / 3 + 1) {
                res.add(num);
            }
        }
        return res;
    }

    //TODO:摩尔投票法
}
