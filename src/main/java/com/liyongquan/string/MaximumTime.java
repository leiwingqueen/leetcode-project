package com.liyongquan.string;

import javax.rmi.CORBA.Tie;

/**
 * @author liyongquan
 * @date 2021/7/25
 */
public class MaximumTime {
    /**
     * 暴力解法
     *
     * @param time
     * @return
     */
    public String maximumTime(String time) {
        for (int i = 23; i >= 0; i--) {
            for (int j = 59; j >= 0; j--) {
                String str = toStr(i, j);
                if (match(time, str)) {
                    return str;
                }
            }
        }
        return "";
    }

    private boolean match(String format, String time) {
        for (int i = 0; i < format.length(); i++) {
            if (time.charAt(i) != '?' && format.charAt(i) != time.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private String toStr(int hour, int minute) {
        StringBuilder sb = new StringBuilder();
        if (hour < 10) {
            sb.append("0");
        }
        sb.append(hour);
        sb.append(":");
        if (minute < 10) {
            sb.append("0");
        }
        sb.append(minute);
        return sb.toString();
    }
}
