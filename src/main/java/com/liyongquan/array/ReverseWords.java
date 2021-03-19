package com.liyongquan.array;

//给定一个字符串，逐个翻转字符串中的每个单词。
//
// 示例：
//
// 输入: ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
//输出: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
//
// 注意：
//
//
// 单词的定义是不包含空格的一系列字符
// 输入字符串中不会包含前置或尾随的空格
// 单词与单词之间永远是以单个空格隔开的
//
//
// 进阶：使用 O(1) 额外空间复杂度的原地解法。
// Related Topics 字符串
// 👍 53 👎 0

public class ReverseWords {
    /**
     * 开辟空间
     *
     * @param s
     */
    public void reverseWords(char[] s) {
        if (s.length == 0) {
            return;
        }
        int len = s.length;
        char[] tmp = new char[len];
        int idx = len - 1, idx2 = 0;
        while (idx >= 0) {
            int start = idx;
            while (idx >= 0 && s[idx] != ' ') {
                idx--;
            }
            //单词的范围是(idx,start]
            for (int i = idx + 1; i <= start; i++) {
                tmp[idx2++] = s[i];
            }
            if (idx >= 0) {
                tmp[idx2++] = ' ';
                idx--;
            }
        }
        //拷贝
        for (int i = 0; i < len; i++) {
            s[i] = tmp[i];
        }
    }

    /**
     * 原地算法，每个单词单独翻转，然后再所有字符翻转
     *
     * @param s
     */
    public void reverseWords2(char[] s) {
        if (s.length == 0) {
            return;
        }
        int len = s.length;
        int idx = 0;
        while (idx < len) {
            int start = idx;
            while (idx < len && s[idx] != ' ') {
                idx++;
            }
            //单词的范围是[start,idx),进行反转
            int l = start, r = idx - 1;
            while (l < r) {
                swap(s, l, r);
                l++;
                r--;
            }
            idx++;
        }
        //所有字符反转
        int l = 0, r = len - 1;
        while (l < r) {
            swap(s, l, r);
            l++;
            r--;
        }
    }

    private void swap(char[] s, int l, int r) {
        char tmp = s[l];
        s[l] = s[r];
        s[r] = tmp;
    }
}
