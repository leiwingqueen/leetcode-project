package com.liyongquan.weeklycontest.wc288;

public class MinimizeResult {
    public String minimizeResult(String expression) {
        int len = expression.length();
        int mx = Integer.MAX_VALUE;
        int lIdx = 0;
        int rIdx = 0;
        for (int l = 0; l < len && expression.charAt(l) != '+'; l++) {
            for (int r = len - 1; r >= 0 && expression.charAt(r) != '+'; r--) {
                //括号左边的数字
                int n1 = 1;
                int i = 0;
                if (l > 0) {
                    n1 = 0;
                    for (; i < l; i++) {
                        n1 = n1 * 10 + (expression.charAt(i) - '0');
                    }
                }
                int n2 = 0;
                for (; i < len && expression.charAt(i) != '+'; i++) {
                    n2 = n2 * 10 + (expression.charAt(i) - '0');
                }
                i++;
                int n3 = 0;
                for (; i <= r; i++) {
                    n3 = n3 * 10 + (expression.charAt(i) - '0');
                }
                int n4 = 1;
                if (i < len) {
                    n4 = 0;
                    for (; i < len; i++) {
                        n4 = n4 * 10 + (expression.charAt(i) - '0');
                    }
                }
                int cal = n1 * (n2 + n3) * n4;
                if (cal < mx) {
                    mx = cal;
                    lIdx = l;
                    rIdx = r;
                }
            }
        }
        //输出
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lIdx; i++) {
            sb.append(expression.charAt(i));
        }
        sb.append("(");
        for (int i = lIdx; i <= rIdx; i++) {
            sb.append(expression.charAt(i));
        }
        sb.append(")");
        for (int i = rIdx + 1; i < len; i++) {
            sb.append(expression.charAt(i));
        }
        return sb.toString();
    }
}
