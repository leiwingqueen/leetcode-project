package com.liyongquan.math;

//给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
//
//“最近的”定义为两个整数差的绝对值最小。
//
// 
//
//示例 1:
//
//输入: n = "123"
//输出: "121"
//示例 2:
//
//输入: n = "1"
//输出: "0"
//解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
// 
//
//提示:
//
//1 <= n.length <= 18
//n 只由数字组成
//n 不含前导 0
//n 代表在 [1, 1018 - 1] 范围内的整数
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-the-closest-palindrome
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class NearestPalindromic {
    /**
     * 本质上还是一个贪心，其实没啥意思
     *
     * @param n
     * @return
     */
    public String nearestPalindromic(String n) {
        long num = Long.parseLong(n);
        if (n.length() == 1) {
            return String.valueOf(num - 1);
        }
        long n1 = find(num);
        int pow = (int) Math.pow(10, n.length() / 2);
        long left = num - pow;
        long n2 = find(left);
        if (n2 == num || count(left) != n.length()) {
            n2 = 0;
            for (int i = 0; i < n.length() - 1; i++) {
                n2 = n2 * 10 + 9;
            }
        }
        long right = num + pow;
        long n3 = find(right);
        if (n3 == num || count(right) != n.length()) {
            n3 = 1;
            for (int i = 0; i < n.length() - 1; i++) {
                n3 *= 10;
            }
            n3 = n3 * 10 + 1;
        }
        long res = Long.MAX_VALUE;
        if (check(n1, num, res)) {
            res = n1;
        }
        if (check(n2, num, res)) {
            res = n2;
        }
        if (check(n3, num, res)) {
            res = n3;
        }
        return String.valueOf(res);
    }

    private boolean check(long n, long num, long res) {
        if (n != num && (Math.abs(n - num) < Math.abs(res - num) ||
                (n < num && Math.abs(n - num) <= Math.abs(res - num)))) {
            return true;
        } else {
            return false;
        }
    }

    private long find(long num) {
        char[] arr = String.valueOf(num).toCharArray();
        int l = 0, r = arr.length - 1;
        while (l < r) {
            arr[r--] = arr[l++];
        }
        return Long.parseLong(String.valueOf(arr));
    }

    private int count(long num) {
        int cnt = 0;
        if (num == 0) {
            return 1;
        }
        while (num > 0) {
            cnt++;
            num /= 10;
        }
        return cnt;
    }
}
