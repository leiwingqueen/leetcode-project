package com.liyongquan.weeklycontest.wc292;

public class LargestGoodInteger {
    public String largestGoodInteger(String num) {
        int l = 0, r = 0;
        String res = "";
        while (r < num.length()) {
            if (num.charAt(r) == num.charAt(l)) {
                r++;
            } else {
                if (r - l >= 3 && num.substring(l, l + 3).compareTo(res) > 0) {
                    res = num.substring(l, l + 3);
                }
                l = r;
            }
        }
        if (r - l >= 3 && num.substring(l, l + 3).compareTo(res) > 0) {
            res = num.substring(l, l + 3);
        }
        return res;
    }
}
