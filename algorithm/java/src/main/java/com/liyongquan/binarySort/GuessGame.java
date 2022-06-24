package com.liyongquan.binarySort;

/**
 * 猜数字游戏的规则如下：
 * <p>
 * 每轮游戏，系统都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，系统会告诉你，你猜测的数字比系统选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
 * <p>
 * -1 : 你猜测的数字比系统选出的数字大
 * 1 : 你猜测的数字比系统选出的数字小
 * 0 : 恭喜！你猜对了！
 *  
 * <p>
 * 示例 :
 * <p>
 * 输入: n = 10, pick = 6
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/guess-number-higher-or-lower
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GuessGame {
    /**
     * 二分查找，时间复杂度O(log(n))
     *
     * @param n
     * @return
     */
    public int guessNumber(int n) {
        int left = 1, right = n;
        while (left < right) {
            //关键在这里，不能这样写,int 会溢出
            //int middle = (left + right) / 2;
            int middle = left + (right - left) / 2;
            int guess = guess(middle);
            if (guess == 0) {
                return middle;
            } else if (guess < 0) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return left;
    }

    /**
     * Forward declaration of guess API.
     *
     * @param num your guess
     * @return -1 if num is lower than the guess number
     * 1 if num is higher than the guess number
     * otherwise return 0
     * int guess(int num);
     */
    public int guess(int num) {
        return -1;
    }
}
