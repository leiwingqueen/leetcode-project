package com.liyongquan.tyy;

import java.util.Scanner;

public class Qq {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int yy = in.nextInt();
            int mm = in.nextInt();
            int dd = in.nextInt();
            int[] res = cal(yy, mm, dd);
            System.out.println(res[1] + " " + res[0]);
        }
    }

    /**
     * 根据年月日求出星期几和是一年中的第几天
     *
     * @param yy
     * @param mm
     * @param dd
     * @return
     */
    public static int[] cal(int yy, int mm, int dd) {
        // 先计算距离1970年1月1日的天数
        int days = 0;
        for (int i = 1970; i < yy; i++) {
            days += isLeapYear(i) ? 366 : 365;
        }
        // 今年的第几天
        int r2 = 0;
        for (int i = 1; i < mm; i++) {
            int d = getDays(yy, i);
            days += d;
            r2 += d;
        }
        days += dd - 1;
        r2 += dd;
        // 计算星期几,1970年1月1日是星期四
        int r1 = (4 + days) % 7;
        return new int[]{r1, r2};
    }

    /**
     * 判断是否是闰年
     *
     * @param yy
     * @return
     */
    private static boolean isLeapYear(int yy) {
        return (yy % 4 == 0 && yy % 100 != 0) || yy % 400 == 0;
    }

    /**
     * 获取每个月的天数
     *
     * @param yy
     * @param mm
     * @return
     */
    private static int getDays(int yy, int mm) {
        if (mm == 2) {
            return isLeapYear(yy) ? 29 : 28;
        } else if (mm == 4 || mm == 6 || mm == 9 || mm == 11) {
            return 30;
        } else {
            return 31;
        }
    }
}
