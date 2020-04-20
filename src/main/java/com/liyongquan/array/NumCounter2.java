package com.liyongquan.array;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 2 <= nums <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumCounter2 {
    public int[] singleNumbers(int[] nums) {
        SortedMap<Integer, Integer> map = new TreeMap<>(Integer::compareTo);
        for (int num : nums) {
            map.put(num, (map.containsKey(num) ? map.get(num) : 0) + 1);
        }
        int[] result = new int[2];
        int i = 0;
        for (Integer integer : map.keySet()) {
            result[i] = integer;
            i++;
            if (i == 2) {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        NumCounter2 counter2 = new NumCounter2();
        int[] ints = counter2.singleNumbers(new int[]{4, 1, 4, 6});
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }
}
