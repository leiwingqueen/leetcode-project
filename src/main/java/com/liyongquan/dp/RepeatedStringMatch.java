package com.liyongquan.dp;

//686. 重复叠加字符串匹配
//给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
//
//注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
//
// 
//
//示例 1：
//
//输入：a = "abcd", b = "cdabcdab"
//输出：3
//解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
//示例 2：
//
//输入：a = "a", b = "aa"
//输出：2
//示例 3：
//
//输入：a = "a", b = "a"
//输出：1
//示例 4：
//
//输入：a = "abc", b = "wxyz"
//输出：-1
// 
//
//提示：
//
//1 <= a.length <= 104
//1 <= b.length <= 104
//a 和 b 由小写英文字母组成
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/repeated-string-match
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class RepeatedStringMatch {
    //双指针解法
    //时间复杂度O(m*n)，m是字符串a的长度,n是字符串b的长度
    public int repeatedStringMatch(String a, String b) {
        //查找满足条件的第一个字符
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(0)) {
                continue;
            }
            int cnt = check(a, b, i);
            if (cnt >= 0) {
                return cnt;
            }
        }
        return -1;
    }

    private int check(String a, String b, int idx) {
        int cnt = 1;
        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) != a.charAt(idx)) {
                return -1;
            }
            idx++;
            if (idx == a.length() && i < b.length() - 1) {
                cnt++;
                idx = 0;
            }
        }
        return cnt;
    }

    public int repeatedStringMatch2(String a, String b) {
        //计算最多需要多少个a串
        int cnt = 1 + ((b.length() - 1) / a.length());
        if ((b.length() - 1) % a.length() != 0) {
            cnt++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            sb.append(a);
        }
        int idx = sb.toString().indexOf(b);
        if (idx < 0) {
            return -1;
        }
        int len = idx + b.length();
        return len / a.length() + (len % a.length() != 0 ? 1 : 0);
    }

    //TODO:KMP?
}
