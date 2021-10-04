package com.liyongquan.string;

import com.liyongquan.array.FirstLetter;

/**
 * @author liyongquan
 * @date 2021/10/4
 */
public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String s, int k) {
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '-') {
                size++;
            }
        }
        if (size == 0) {
            return "";
        }
        //第一个分组的长度
        int first = size % k;
        if (first == 0) {
            first = k;
        }
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        int cnt = 0;
        //第一个分组
        while (cnt < first) {
            char ch = toUpper(s.charAt(idx++));
            if (ch != '-') {
                cnt++;
                sb.append(ch);
            }
        }
        while (idx < s.length()) {
            StringBuilder builder = new StringBuilder();
            cnt = 0;
            while (idx < s.length() && cnt < k) {
                char ch = toUpper(s.charAt(idx++));
                if (ch != '-') {
                    cnt++;
                    builder.append(ch);
                }
            }
            if (builder.length() > 0) {
                sb.append("-" + builder.toString());
            }
        }
        return sb.toString();
    }

    private char toUpper(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            ch = (char) (ch - 'a' + 'A');
        }
        return ch;
    }
}
