package com.liyongquan.slidewindow;

/**
 * 395. 至少有K个重复字符的最长子串
 * <p>
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2：
 * <p>
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅由小写英文字母组成
 * 1 <= k <= 105
 * 通过次数21,384提交次数45,455
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubstring2 {
    /**
     * 暴力+前缀和
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring2(String s, int k) {
        int len = s.length();
        if (k == 1) {
            return len;
        }
        //类似前缀和的做法
        int[][] prefixSum = new int[len + 1][26];
        for (int i = 1; i <= len; i++) {
            //从上一行先拷贝下来
            for (int j = 0; j < 26; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j];
            }
            prefixSum[i][s.charAt(i - 1) - 'a']++;
        }
        int res = 0;
        //计算[i,j)子串的出现次数
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                int[] bitmap = new int[26];
                boolean ok = true;
                for (int l = 0; l < 26; l++) {
                    bitmap[l] = prefixSum[j][l] - prefixSum[i][l];
                    if (bitmap[l] > 0 && bitmap[l] < k) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    res = Math.max(res, j - i);
                }
            }
        }
        return res;
    }

    /**
     * 基于上面的解法简单优化一下，由于题解是求最大解，因为我们第二层循环应该从后往前
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring3(String s, int k) {
        int len = s.length();
        if (k == 1) {
            return len;
        }
        //类似前缀和的做法
        int[][] prefixSum = new int[len + 1][26];
        for (int i = 1; i <= len; i++) {
            //从上一行先拷贝下来
            for (int j = 0; j < 26; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j];
            }
            prefixSum[i][s.charAt(i - 1) - 'a']++;
        }
        int res = 0;
        //计算[i,j)子串的出现次数
        for (int i = 0; i < len; i++) {
            //简单优化，从后往前，并且如果j-i<res的就可以直接终止这一层的循环了
            for (int j = len; j > i && j - i > res; j--) {
                int[] bitmap = new int[26];
                boolean ok = true;
                for (int l = 0; l < 26; l++) {
                    bitmap[l] = prefixSum[j][l] - prefixSum[i][l];
                    if (bitmap[l] > 0 && bitmap[l] < k) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    res = Math.max(res, j - i);
                }
            }
        }
        return res;
    }

    /**
     * 滑动窗口算法
     * <p>
     * 使用一个数组对窗口的每个字符的出现数量进行统计。
     * 另外还需要一个变量对不满足条件的字符数量进行统计
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        int len = s.length();
        if (k == 1) {
            return len;
        }
        int l = 0, r = 0;
        int[] bitmap = new int[26];
        //不满足条件的字符总数
        int cnt = 0;
        int res = 0;
        while (r < len) {
            //右指针移动
            int idx = s.charAt(r) - 'a';
            bitmap[idx]++;
            //第一次出现
            if (bitmap[idx] == 1) {
                cnt++;
            } else if (bitmap[idx] == k) {
                //刚好满足条件
                cnt--;
            }
            r++;
            //TODO:左指针应该如何移动？
        }
        return 0;
    }
}
