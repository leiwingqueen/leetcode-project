package com.liyongquan.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 316. 去除重复字母
 * <p>
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * <p>
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 * <p>
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicateLetters {
    /**
     * 单调栈
     *
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        int len = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < len; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        Deque<Character> stack = new LinkedList<>();
        boolean[] exist = new boolean[26];
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            cnt[ch - 'a']--;
            if (exist[ch - 'a']) {
                continue;
            }
            while (!stack.isEmpty() && stack.peekLast() > ch && cnt[stack.peekLast() - 'a'] > 0) {
                Character poll = stack.pollLast();
                exist[poll - 'a'] = false;
            }
            stack.offerLast(ch);
            exist[ch - 'a'] = true;
        }
        //输出结果
        StringBuilder sb = new StringBuilder(stack.size());
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }
        return sb.toString();
    }

}
