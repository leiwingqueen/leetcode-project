package com.liyongquan.design;

//给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
//
//注意：
//数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
//
//示例:
//
//int[] nums = new int[] {1,2,3,3,3};
//Solution solution = new Solution(nums);
//
//// pick(3) 应该返回索引 2,3 或者 4。每个索引的返回概率应该相等。
//solution.pick(3);
//
//// pick(1) 应该返回 0。因为只有nums[0]等于1。
//solution.pick(1);
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/random-pick-index
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPickIndex {
    private Map<Integer, List<Integer>> map = new HashMap<>();

    public RandomPickIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        int idx = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(idx);
    }

    //TODO:水塘抽样
}
