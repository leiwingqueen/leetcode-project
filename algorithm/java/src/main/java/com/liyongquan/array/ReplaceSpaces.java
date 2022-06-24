package com.liyongquan.array;

/**
 * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 * <p>
 * 示例1:
 * <p>
 * 输入："Mr John Smith    ", 13
 * 输出："Mr%20John%20Smith"
 * 示例2:
 * <p>
 * 输入："               ", 5
 * 输出："%20%20%20%20%20"
 * 提示：
 * <p>
 * 字符串长度在[0, 500000]范围内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-url-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReplaceSpaces {
    public String replaceSpaces(String S, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            if (c == ' ') {
                builder.append("%20");
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * 官方的题意是需要你做原地操作，但是由于java的字符串是不可变的，因此改用字符数组
     *
     * @param S
     * @param length
     * @return
     */
    public String replaceSpaces2(String S, int length) {
        char[] s = new char[S.length()];
        int j = 0;
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            if (c == ' ') {
                s[j] = '%';
                s[j + 1] = '2';
                s[j + 2] = '0';
                j += 3;
            } else {
                s[j] = c;
                j++;
            }
        }
        return new String(s, 0, j);
    }
}
