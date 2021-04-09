package com.liyongquan.weeklycontest.wc235;

public class TruncateSentence {
    public String truncateSentence(String s, int k) {
        int len = s.length();
        int idx = 0, cnt = 0;
        StringBuilder sb = new StringBuilder();
        while (idx < len && cnt < k) {
            //查找下一个单词
            while (idx < len && s.charAt(idx) != ' ') {
                sb.append(s.charAt(idx++));
            }
            cnt++;
            if (idx < len && cnt != k) {
                //只有一个空格
                sb.append(s.charAt(idx++));
            }
        }
        return sb.toString();
    }
}
