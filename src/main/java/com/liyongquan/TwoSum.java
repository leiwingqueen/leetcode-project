package com.liyongquan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TwoSum {
    /**
     * 暴力解法
     * 时间复杂度O(n^2)，空间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            int a = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int b = nums[j];
                if (a + b == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    /**
     * 使用Map，时间复杂度可以简化成O(n)，空间复杂度O(n)
     * <p>
     * 不通过，题目并没有保证一个元素只出现一次。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (Map.Entry<Integer, Integer> kv : map.entrySet()) {
            if (map.containsKey(target - kv.getKey())) {
                return new int[]{kv.getValue(), map.get(target - kv.getKey())};
            }
        }
        return new int[]{};
    }

    /**
     * dp解法
     * f(k,n)为前k个元素组成n的下标。
     * 则
     * f(k,n)=f(k-1,n)||[getIdx(n-nums[k]),k]
     * <p>
     * getIdx为n-nums[k]的第一个下标。
     * <p>
     * dp表达式的意思是
     * 1.选择前k-1个元素组成n
     * 2.前面k-1个元素选择一个，另外加上第k个元素
     * <p>
     * 由于dp表达式只涉及到前面一个元素，我们直接使用一个变量就可以完成
     * <p>
     * 时间复杂度O(2*n)，空间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
        if (nums.length < 2) {
            return new int[]{};
        }
        if (nums.length == 2) {
            return nums[0] + nums[1] == target ? new int[]{0, 1} : new int[]{};
        }
        //保存第一个下标
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            }
        }
        //初始化
        if (nums[0] + nums[1] == target) {
            return new int[]{0, 1};
        }
        //dp迭代
        for (int i = 3; i <= nums.length; i++) {
            if (map.containsKey(target - nums[i - 1]) && map.get(target - nums[i - 1]) < i - 1) {
                return new int[]{map.get(target - nums[i - 1]), i - 1};
            }
        }
        return new int[]{};
    }

    /**
     * 思路：
     * 假设前k-1个元素存在一个为target-nums[k]，则表示我们已经找到结果了
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum4(int[] nums, int target) {
        if (nums.length < 2) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 7, 11, 11, 15};
        TwoSum solution = new TwoSum();
        int[] ints = solution.twoSum4(a, 9);
        System.out.println(ints[0] + " " + ints[1]);
    }
}
