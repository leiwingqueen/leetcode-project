package com.liyongquan.array;

import com.liyongquan.linklist.AddTwoNumber;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。
 *
 * 示例:
 *
 * s = "abaccdeff"
 * 返回 "b"
 *
 * s = ""
 * 返回 " "
 *
 *
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 50000
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FirstLetter {
    public char firstUniqChar(String s) {
        if (s==null||s.length()<=0) {
            return ' ';
        }
        Map<Character,Boolean> set=new LinkedHashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.containsKey(c)) {
                set.put(c,true);
            }else{
                set.put(c,false);
            }
        }
        for (Map.Entry<Character, Boolean> entry : set.entrySet()) {
            if (entry.getValue()) {
                return entry.getKey();
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        FirstLetter letter=new FirstLetter();
        char firstUniqChar = letter.firstUniqChar("aadadaad");
        System.out.println(firstUniqChar);
    }
}
