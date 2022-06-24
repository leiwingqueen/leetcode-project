package com.liyongquan.array;

/**
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 * <p>
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 * <p>
 * 回文串不一定是字典当中的单词。
 * <p>
 *  
 * <p>
 * 示例1：
 * <p>
 * 输入："tactcoa"
 * 输出：true（排列有"tacocat"、"atcocta"，等等）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-permutation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanPermutePalindrome {
    /**
     * 若存在两个/以上的字符数量为奇数，则肯定是回文串排序
     *
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        int[] bitmap = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            bitmap[c]++;
        }
        boolean r = false;
        for (int i = 0; i < bitmap.length; i++) {
            if ((bitmap[i] & 0x1) == 1) {
                if (r) {
                    return false;
                }
                r = true;
            }
        }
        return true;
    }
}
