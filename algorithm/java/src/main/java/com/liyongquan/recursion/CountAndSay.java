package com.liyongquan.recursion;

/**
 * @author liyongquan
 * @date 2021/10/15
 */
public class CountAndSay {
    /**
     * 递归解法
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String s = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        int l = 0, r = 0;
        while (r < s.length()) {
            if (l == r || s.charAt(r) == s.charAt(r - 1)) {
                r++;
            } else {
                sb.append(String.valueOf(r - l) + s.charAt(l));
                l = r;
            }
        }
        sb.append(String.valueOf(r - l) + s.charAt(l));
        return sb.toString();
    }

    /**
     * 改成迭代
     *
     * @param n
     * @return
     */
    public String countAndSay2(int n) {
        String s = "1";
        for (int i = 1; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int l = 0, r = 0;
            while (r < s.length()) {
                if (l == r || s.charAt(r) == s.charAt(r - 1)) {
                    r++;
                } else {
                    sb.append(String.valueOf(r - l) + s.charAt(l));
                    l = r;
                }
            }
            sb.append(String.valueOf(r - l) + s.charAt(l));
            s = sb.toString();
        }
        return s;
    }
}
