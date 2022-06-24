package com.liyongquan.dp;

//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
//
// 
//
//示例 1:
//
//输入: 12258
//输出: 5
//解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
// 
//
//提示：
//
//0 <= num < 231
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyongquan
 * @date 2021/9/10
 */
public class TranslateNum {
    public int translateNum(int num) {
        List<Integer> list = convert(num);
        if (list.size() <= 1) {
            return 1;
        }
        int[] dp = new int[list.size() + 1];
        dp[0] = 1;
        for (int i = 1; i <= list.size(); i++) {
            int idx = list.size() - i;
            dp[i] = dp[i - 1];
            //假设idx和idx+1能拼成一个字符
            if (i >= 2) {
                int concat = list.get(idx + 1) * 10 + list.get(idx);
                if (concat >= 10 && concat <= 25) {
                    dp[i] += dp[i - 2];
                }
            }
        }
        return dp[list.size()];
    }

    private List<Integer> convert(int num) {
        List<Integer> res = new ArrayList<>();
        while (num > 0) {
            res.add(num % 10);
            num /= 10;
        }
        if (res.size() == 0) {
            res.add(0);
        }
        return res;
    }
}
