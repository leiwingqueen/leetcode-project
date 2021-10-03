package com.liyongquan.math;

/**
 * @author liyongquan
 * @date 2021/10/2
 */
public class ToHex {
    /**
     * 计算机本身就是2进制的补码，4个bit就是16进制
     *
     * @param num
     * @return
     */
    public String toHex(int num) {
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int mod = num & 0xf;
            char ch = mod < 10 ? (char) ('0' + mod) : (char) ('a' + mod - 10);
            sb.append(ch);
            num >>>= 4;
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.reverse().toString();
    }
}
