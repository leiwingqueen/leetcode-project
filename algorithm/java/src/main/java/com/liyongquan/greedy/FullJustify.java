package com.liyongquan.greedy;

//给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
//
//你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
//
//要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
//
//文本的最后一行应为左对齐，且单词之间不插入额外的空格。
//
//说明:
//
//单词是指由非空格字符组成的字符序列。
//每个单词的长度大于 0，小于等于 maxWidth。
//输入单词数组 words 至少包含一个单词。
//示例:
//
//输入:
//words = ["This", "is", "an", "example", "of", "text", "justification."]
//maxWidth = 16
//输出:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
//示例 2:
//
//输入:
//words = ["What","must","be","acknowledgment","shall","be"]
//maxWidth = 16
//输出:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
//     因为最后一行应为左对齐，而不是左右两端对齐。
//     第二行同样为左对齐，这是因为这行只包含一个单词。
//示例 3:
//
//输入:
//words = ["Science","is","what","we","understand","well","enough","to","explain",
//         "to","a","computer.","Art","is","everything","else","we","do"]
//maxWidth = 20
//输出:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//]
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/text-justification
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.LinkedList;
import java.util.List;

/**
 * @author liyongquan
 * @date 2021/9/9
 */
public class FullJustify {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int l = 0, r = 0;
        int width = 0;
        List<String> res = new LinkedList<>();
        while (r < words.length) {
            width += words[r].length();
            if (l != r) {
                //左边需要预留一个空格
                width++;
            }
            if (width > maxWidth) {
                width -= words[r].length() + 1;
                String str = concat(words, l, r, width, maxWidth);
                res.add(str);
                width = 0;
                l = r;
            } else {
                r++;
            }
        }
        res.add(concat(words, l, r, width, maxWidth));
        return res;
    }

    /**
     * 拼接[l,r)之间的字符
     *
     * @param words
     * @param l
     * @param r
     * @param width
     * @param maxWidth
     * @return
     */
    private String concat(String[] words, int l, int r, int width, int maxWidth) {
        //只有一个单词和最后一行的场景
        if (r - l == 1 || r == words.length) {
            StringBuilder sb = new StringBuilder();
            for (int i = l; i < r; i++) {
                if (i > l) {
                    sb.append(' ');
                }
                sb.append(words[i]);
            }
            for (int i = 0; i < maxWidth - width; i++) {
                sb.append(' ');
            }
            return sb.toString();
        } else {
            //中间需要填充的空格
            int space = (maxWidth - width) / (r - l - 1) + 1;
            //左右需要填充的空格
            int lSpace = (maxWidth - width) % (r - l - 1);
            StringBuilder sb = new StringBuilder();
            for (int i = l; i < r; i++) {
                if (i > l) {
                    for (int j = 0; j < space; j++) {
                        sb.append(' ');
                    }
                    if (lSpace > 0) {
                        sb.append(' ');
                        lSpace--;
                    }
                }
                sb.append(words[i]);
            }
            return sb.toString();
        }
    }
}
