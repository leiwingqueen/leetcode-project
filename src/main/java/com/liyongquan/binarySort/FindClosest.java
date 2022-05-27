package com.liyongquan.binarySort;

//有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
//
//示例：
//
//输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
//输出：1
//提示：
//
//words.length <= 100000
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-closest-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class FindClosest {
    public int findClosest(String[] words, String word1, String word2) {
        int l = 1, r = word1.length();
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (check(words, word1, word2, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l - 1;
    }

    private boolean check(String[] words, String word1, String word2, int distance) {
        int cnt1 = 0;
        int cnt2 = 0;
        int p1 = 0;
        int p2 = 0;
        while (p2 < word2.length()) {
            if (p2 - p1 > distance) {
                if (words[p1].equals(word1)) {
                    cnt1--;
                } else if (words[p1].equals(word2)) {
                    cnt2--;
                }
                p1++;
            }
            if (words[p2].equals(word1)) {
                cnt1++;
            } else if (words[p2].equals(word2)) {
                cnt2++;
            }
            if (cnt1 > 0 && cnt2 > 0) {
                return true;
            }
            p2++;
        }
        return false;
    }
}
