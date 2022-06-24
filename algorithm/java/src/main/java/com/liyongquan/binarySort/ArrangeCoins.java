package com.liyongquan.binarySort;

/**
 * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
 * <p>
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 * <p>
 * n 是一个非负整数，并且在32位有符号整型的范围内。
 * <p>
 * 示例 1:
 * <p>
 * n = 5
 * <p>
 * 硬币可排列成以下几行:
 * ¤
 * ¤ ¤
 * ¤ ¤
 * <p>
 * 因为第三行不完整，所以返回2.
 * 示例 2:
 * <p>
 * n = 8
 * <p>
 * 硬币可排列成以下几行:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 * <p>
 * 因为第四行不完整，所以返回3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arranging-coins
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ArrangeCoins {
    /**
     * 假设返回为x，则x行的coin数量为1+2+...+x=(1+x)*x/2
     * 则我们需要找到(1+x)*x/2<=n。所有x中最大的解
     *
     * 二分查找
     * @param n
     * @return
     */
    public int arrangeCoins(int n) {
        if (n <= 1) {
            return n;
        }
        long left = 0, right = n;
        while (left < right) {
            long middle = (left + right) / 2;
            /*System.out.println(String.format("left:%s,right:%s,middle:%s",left,right,middle));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            long r = middle * (middle + 1) / 2;
            if (r == n) {
                return (int) middle;
            } else if (r > n) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        long l = left * (left + 1) / 2;
        return l <= n ? (int) left : (int) left - 1;
    }

    public static void main(String[] args) {
        ArrangeCoins coins = new ArrangeCoins();
        int i = coins.arrangeCoins(1804289383);
        System.out.println(i);
    }
}
