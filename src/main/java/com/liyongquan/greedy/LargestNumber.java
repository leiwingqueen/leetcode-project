package com.liyongquan.greedy;

import java.util.*;

/**
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [10,2]
 * 输出: 210
 * 示例 2:
 * <p>
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(String.valueOf(nums[i]));
        }
        Collections.sort(list, (o1, o2) -> {
            int i = 0, j = 0;
            while (i < o1.length() && j < o2.length()) {
                if (o2.charAt(j) != o1.charAt(i)) {
                    return o2.charAt(j) - o1.charAt(i);
                }
                i++;
                j++;
            }
            if (i >= o1.length()) {
                i--;
            }
            if (j >= o2.length()) {
                j--;
            }
            return o2.charAt(j) - o1.charAt(i);
        });
        StringBuilder builder = new StringBuilder();
        for (String s : list) {
            builder.append(s);
        }
        return builder.toString().startsWith("0") ? "0" : builder.toString();
    }

    public static void main(String[] args) {
        LargestNumber number = new LargestNumber();
        String s = number.largestNumber(new int[]{3, 30, 34, 5, 9});
        System.out.println(s);
    }
}
