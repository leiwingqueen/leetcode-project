package com.liyongquan.string;

/**
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * <p>
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 * <p>
 * 示例:
 * <p>
 * 输入: "Hello, my name is John"
 * 输出: 5
 * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-segments-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountSegments {
    public int countSegments(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return s.split(" ").length;
    }

    public static void main(String[] args) {
        CountSegments countSegments = new CountSegments();
        int i = countSegments.countSegments(", , , ,        a, eaefa");
        //官方要求返回的答案是6，不明白为啥是6。。。
        System.out.println(i);
    }
}
