package com.liyongquan.weeklycontest.wc291;

public class RemoveDigit {
    public String removeDigit(String number, char digit) {
        String res = "";
        int len = number.length();
        for (int i = 0; i < len; i++) {
            if (number.charAt(i) == digit) {
                StringBuilder sb = new StringBuilder();
                if (i > 0) {
                    sb.append(number, 0, i);
                }
                if (i + 1 < len) {
                    sb.append(number, i + 1, len);
                }
                if (sb.toString().compareTo(res) > 0) {
                    res = sb.toString();
                }
            }
        }
        return res;
    }
}
