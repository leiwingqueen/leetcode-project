package com.liyongquan.array;

/**
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串"I am a student. "，则输出"student. a am I"。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * <p>
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * <p>
 * 示例 3：
 * <p>
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * <p>
 * <p>
 * 说明：
 * <p>
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RevertString {
    public String reverseWords(String s) {
        String[] split = s.split(" ");
        int l = 0;
        int r = split.length - 1;
        while (l < r) {
            while ("".equals(split[l])&&l<split.length-1){
                l++;
            }
            while ("".compareTo(split[r])==0&&r>0){
                r--;
            }
            if (l>=r) {
                break;
            }
            String temp = split[l];
            split[l] = split[r];
            split[r] = temp;
            l++;
            r--;
        }
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            String s1 = split[i];
            if ("".equals(s1)) {
                continue;
            }
            builder.append(s1);
            if (i!=split.length-1) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        RevertString revertString=new RevertString();
        String s = revertString.reverseWords("   a   b ");
        System.out.println(s);
    }
}
