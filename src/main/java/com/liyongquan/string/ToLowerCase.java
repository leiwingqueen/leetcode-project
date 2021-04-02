package com.liyongquan.string;

public class ToLowerCase {
    public String toLowerCase(String str) {
        int len = str.length();
        if (len == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(len);
        int distance = 'a' - 'A';
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                sb.append((char)(ch + distance));
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
