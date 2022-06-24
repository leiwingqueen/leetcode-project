package com.liyongquan.array;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * s = "leetcode"
 * 返回 0
 * <p>
 * s = "loveleetcode"
 * 返回 2
 *  
 * <p>
 * 提示：你可以假定该字符串只包含小写字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FirstUniqChar {
    public int firstUniqChar(String s) {
        //出现次数
        int[] bitmap = new int[26];
        //第一次出现的位置
        int[] position = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = c - 'a';
            bitmap[index]++;
            if (position[index] == 0) {
                position[index] = i + 1;
            }
        }
        int first = -1;
        for (int i = 0; i < bitmap.length; i++) {
            if (bitmap[i] == 1) {
                if (first < 0 || position[i] - 1 < first) {
                    first = position[i] - 1;
                }
            }
        }
        return first;
    }

    public static void main(String[] args) {
        FirstUniqChar fu = new FirstUniqChar();
        int f = fu.firstUniqChar("dabbcb");
        System.out.println(f);
    }
}
