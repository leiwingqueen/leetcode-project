package com.liyongquan.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Sum3Num {
    public List<List<Integer>> threeSum(int[] nums) {
        int[] ints = Arrays.stream(nums).sorted().toArray();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < ints.length - 2; i++) {
            for (int j = i + 1; j < ints.length - 1; j++) {
                for (int k = j+1; k < ints.length; k++) {
                    if (ints[i] + ints[j] + ints[k] == 0) {
                        result.add(Arrays.asList(ints[i], ints[j], ints[k]));
                    }
                }
            }
        }
        return result;
    }
}
