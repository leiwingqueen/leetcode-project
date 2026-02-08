package com.liyongquan.string;

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
        String[] split = time.split(":");
        String s1 = "";
        for (int i = 23; i >= 0; i--) {
            s1 = toStr(i);
            if (match(split[0], s1)) {
                break;
            }
        }
        String s2 = "";
        for (int i = 59; i >= 0; i--) {
            s2 = toStr(i);
            if (match(split[1], s2)) {
                break;
            }
        }
        return s1 + ":" + s2;
    }

    public boolean match(String format, String time) {
        for (int i = 0; i < format.length(); i++) {
            if (format.charAt(i) != '?' && format.charAt(i) != time.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private String toStr(int hour) {
        StringBuilder sb = new StringBuilder();
        if (hour < 10) {
            sb.append("0");
        }
        sb.append(hour);
        return sb.toString();
    }
}
