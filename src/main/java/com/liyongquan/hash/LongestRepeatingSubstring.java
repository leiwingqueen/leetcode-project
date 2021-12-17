package com.liyongquan.hash;

//1062. 最长重复子串
//给定字符串 S，找出最长重复子串的长度。如果不存在重复子串就返回 0。
//
// 
//
//示例 1：
//
//输入："abcd"
//输出：0
//解释：没有重复子串。
//示例 2：
//
//输入："abbaba"
//输出：2
//解释：最长的重复子串为 "ab" 和 "ba"，每个出现 2 次。
//示例 3：
//
//输入："aabcaabdaab"
//输出：3
//解释：最长的重复子串为 "aab"，出现 3 次。
//示例 4：
//
//输入："aaaaa"
//输出：4
//解释：最长的重复子串为 "aaaa"，出现 2 次。
// 
//
//提示：
//
//字符串 S 仅包含从 'a' 到 'z' 的小写英文字母。
//1 <= S.length <= 1500
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-repeating-substring
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingSubstring {
    /**
     * 暴力解法
     *
     * 超出内存限制
     *
     * @param s
     * @return
     */
    public int longestRepeatingSubstring(String s) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String sub = s.substring(i, j);
                Integer cnt = map.getOrDefault(sub, 0);
                map.put(sub, cnt + 1);
            }
        }
        int max = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                max = Math.max(max, entry.getKey().length());
            }
        }
        return max;
    }
}
