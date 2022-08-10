package com.liyongquan.weeklycontest.wc301;

public class CanChange {
    public boolean canChange(String start, String target) {
        int p1 = 0, p2 = 0;
        int n = start.length();
        while (true) {
            while (p1 < n && start.charAt(p1) == '_') {
                p1++;
            }
            while (p2 < n && target.charAt(p2) == '_') {
                p2++;
            }
            if (p1 == n && p2 == n) {
                return true;
            }
            if (p1 == n || p2 == n) {
                return false;
            }
            //相对位置不变
            if (start.charAt(p1) != target.charAt(p2)) {
                return false;
            }
            if (start.charAt(p1) == 'L') {
                if (p1 < p2) {
                    return false;
                }
            } else {
                if (p1 > p2) {
                    return false;
                }
            }
            p1++;
            p2++;
        }
    }
}
