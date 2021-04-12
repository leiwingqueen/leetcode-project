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
    /**
     * 不通过
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(String.valueOf(nums[i]));
        }
        Collections.sort(list, (o1, o2) -> {
            int i = 0, j = 0, c = 0;
            while (c <= Math.max(o1.length(), o2.length())) {
                if (o2.charAt(j) != o1.charAt(i)) {
                    return o2.charAt(j) - o1.charAt(i);
                }
                i = (i + 1) % o1.length();
                j = (j + 1) % o2.length();
                c++;
            }
            return 0;
        });
        StringBuilder builder = new StringBuilder();
        for (String s : list) {
            builder.append(s);
        }
        return builder.toString().startsWith("0") ? "0" : builder.toString();
    }

    public String largestNumber2(int[] nums) {
        List<String> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(String.valueOf(nums[i]));
        }
        Collections.sort(list, (o1, o2) ->
                (o2 + o1).compareTo(o1 + o2));
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
        String s1 = number.largestNumber(new int[]{824, 938, 1399, 5607, 6973, 5703, 9609, 4398, 8247});
        System.out.println(s1);
        s = number.largestNumber(new int[]{121, 12});
        System.out.println(s);
    }
}
