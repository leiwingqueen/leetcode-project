package com.liyongquan.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * <p>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * <p>
 * 示例1:
 * <p>
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] split = s.split(" ");
        if (split.length != pattern.length()) {
            return false;
        }
        Map<String, Character> m1 = new HashMap<>(split.length);
        Map<Character, String> m2 = new HashMap<>(split.length);
        for (int i = 0; i < split.length; i++) {
            char ch = pattern.charAt(i);
            if (m1.containsKey(split[i])) {
                Character c = m1.get(split[i]);
                if (c.charValue() != ch) {
                    return false;
                }
            } else {
                if (m2.containsKey(ch)&&!m2.get(ch).equals(split[i])) {
                    return false;
                }
                m1.put(split[i], ch);
                m2.put(ch, split[i]);
            }
        }
        return true;
    }
}
