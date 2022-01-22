package com.liyongquan.string;

//1332. 删除回文子序列
//给你一个字符串 s，它仅由字母 'a' 和 'b' 组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
//
//返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
//
//「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。
//
//「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。
//
// 
//
//示例 1：
//
//输入：s = "ababa"
//输出：1
//解释：字符串本身就是回文序列，只需要删除一次。
//示例 2：
//
//输入：s = "abb"
//输出：2
//解释："abb" -> "bb" -> "".
//先删除回文子序列 "a"，然后再删除 "bb"。
//示例 3：
//
//输入：s = "baabb"
//输出：2
//解释："baabb" -> "b" -> "".
//先删除回文子序列 "baab"，然后再删除 "b"。
// 
//
//提示：
//
//1 <= s.length <= 1000
//s 仅包含字母 'a'  和 'b'
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/remove-palindromic-subsequences
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class RemovePalindromeSub {
    /**
     * 先来个贪心
     * <p>
     * 不正确
     *
     * @param s
     * @return
     */
    public int removePalindromeSub(String s) {
        char[] arr = s.toCharArray();
        int len = s.length();
        int l = 0;
        int cnt = len;
        int res = 0;
        while (cnt > 0) {
            int r = len - 1;
            while (l <= r) {
                if (l == r) {
                    arr[l] = ' ';
                    cnt--;
                    l++;
                } else {
                    while (r > l && arr[r] != arr[l]) {
                        r--;
                    }
                    arr[l] = ' ';
                    arr[r] = ' ';
                    r--;
                    l++;
                    cnt -= 2;
                }
            }
            res++;
        }
        return res;
    }

    /**
     * 看了题解，脑筋急转弯的题目。。。
     *
     * @param s
     * @return
     */
    public int removePalindromeSub2(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r && s.charAt(l) == s.charAt(r)) {
            l++;
            r--;
        }
        return l >= r ? 1 : 2;
    }
}
