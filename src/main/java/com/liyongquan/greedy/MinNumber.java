package com.liyongquan.greedy;

//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
//
// 
//
//示例 1:
//
//输入: [10,2]
//输出: "102"
//示例 2:
//
//输入: [3,30,34,5,9]
//输出: "3033459"
// 
//
//提示:
//
//0 < nums.length <= 100
//说明:
//
//输出结果可能非常大，所以你需要返回一个字符串而不是整数
//拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyongquan
 * @date 2021/9/7
 */
public class MinNumber {
    /**
     * 贪心
     *
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        List<String> list = new ArrayList<>(nums.length);
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        //这里有可能越界
        list.sort((o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            int p = 0;
            while (p < s1.length() && s1.charAt(p) == s2.charAt(p)) {
                p++;
            }
            if (p == s1.length()) {
                return 0;
            }
            return s1.charAt(p) - s2.charAt(p);
        });
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        //去掉前缀0
        /*while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }*/
        return sb.toString();
    }

    //TODO:如何证明这个逻辑的正确性？
}
