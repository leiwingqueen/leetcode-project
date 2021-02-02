package com.liyongquan.slidewindow;

/**
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * <p>
 * 注意:
 * 字符串长度 和 k 不会超过 104。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * s = "ABAB", k = 2
 * <p>
 * 输出:
 * 4
 * <p>
 * 解释:
 * 用两个'A'替换为两个'B',反之亦然。
 * 示例 2:
 * <p>
 * 输入:
 * s = "AABABBA", k = 1
 * <p>
 * 输出:
 * 4
 * <p>
 * 解释:
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-repeating-character-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CharacterReplacement {
    /**
     * https://leetcode-cn.com/problems/longest-repeating-character-replacement/solution/tong-guo-ci-ti-liao-jie-yi-xia-shi-yao-shi-hua-don/
     * 看这个解题
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int l = 0, r = 0;
        int[] bitmap = new int[26];
        int hisMax = 0;
        while (r < s.length()) {
            //窗口右移动
            int pos = s.charAt(r) - 'A';
            bitmap[pos]++;
            r++;
            //更新窗口内的相同字符(这里不是很理解)
            hisMax = Math.max(bitmap[pos], hisMax);
            if (r - l > hisMax + k) {
                //窗口平移
                bitmap[s.charAt(l) - 'A']--;
                l++;
            }
        }
        //返回窗口大小
        return r - l;
    }



    /**
     * 更新一种暴力解法
     *
     * 不通过。考虑ABBB的场景
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement2(String s, int k) {
        int l = 0, r = 0;
        //小优化，每次的l都会从第一次不同的点开始移动
        int firstDiff = -1;
        int res = 0;
        while (l < s.length()) {
            //看最远能移动到哪里
            int tmp = k;
            while (r < s.length() && (tmp > 0 || s.charAt(r) == s.charAt(l))) {
                if (s.charAt(r) != s.charAt(l)) {
                    tmp--;
                    if (firstDiff == -1) {
                        firstDiff = r;
                    }
                }
                r++;
            }
            //更新结果
            res = Math.max(res, r - l);
            //更新l节点
            if (firstDiff >= 0) {
                l = firstDiff;
                firstDiff = -1;
            } else {
                l = r;
            }
            r = l;
        }
        return res;
    }
}
