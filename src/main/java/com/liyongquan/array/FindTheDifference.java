package com.liyongquan.array;

/**
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * <p>
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * <p>
 * 请找出在 t 中被添加的字母。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入：
 * s = "abcd"
 * t = "abcde"
 * <p>
 * 输出：
 * e
 * <p>
 * 解释：
 * 'e' 是那个被添加的字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindTheDifference {
    /**
     * 不能通过，因为字符串有可能是经过重排的
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == t.charAt(i)) {
            i++;
        }
        return t.charAt(i);
    }


    /**
     * 使用map解决
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference2(String s, String t) {
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
            map[t.charAt(i) - 'a']--;
        }
        map[t.charAt(t.length() - 1) - 'a']--;
        for (int i = 0; i < map.length; i++) {
            if (map[i] < 0) {
                return (char) (i + 'a');
            }
        }
        return ' ';
    }

    /**
     * 异或运算
     *
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference3(String s, String t) {
        char c = t.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            c ^= s.charAt(i);
        }
        for (int i = 1; i < t.length(); i++) {
            c ^= t.charAt(i);
        }
        return c;
    }
}
