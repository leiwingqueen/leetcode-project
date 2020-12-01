package com.liyongquan.slidewindow;

/**
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * <p>
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * <p>
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * <p>
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/grumpy-bookstore-owner
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSatisfied {
    /**
     * 滑动窗口算法，只是做了一点修改
     * <p>
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        //初始化第一个窗口
        int len = customers.length;
        int sum = 0;
        for (int i = 0; i < X; i++) {
            sum += customers[i];
        }
        for (int i = X; i < len; i++) {
            if (grumpy[i] == 0) {
                sum += customers[i];
            }
        }
        int max = sum;
        //时间窗口往后迭代
        for (int i = 1; i <= len - X; i++) {
            if (grumpy[i - 1] == 1) {
                sum -= customers[i - 1];
            }
            if (grumpy[i + X - 1] == 1) {
                sum += customers[i + X - 1];
            }
            //System.out.println("i:" + i + ",sum:" + sum);
            max = Math.max(max, sum);
        }
        return max;
    }
}
