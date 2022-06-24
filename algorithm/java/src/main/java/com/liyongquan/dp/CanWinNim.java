package com.liyongquan.dp;

/**
 * 292. Nim 游戏
 * <p>
 * 你和你的朋友，两个人一起玩 Nim 游戏：
 * <p>
 * 桌子上有一堆石头。
 * 你们轮流进行自己的回合，你作为先手。
 * 每一回合，轮到的人拿掉 1 - 3 块石头。
 * 拿掉最后一块石头的人就是获胜者。
 * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4
 * 输出：false
 * 解释：如果堆中有 4 块石头，那么你永远不会赢得比赛；
 *      因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：n = 2
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nim-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CanWinNim {
    /**
     * f(n)=!f(n-1)||!f(n-2)||!f(n-3)
     * <p>
     * 我们可以把空间复杂度直接优化到O(1)
     * <p>
     * 时间复杂度O(n)
     * <p>
     * 超时-因为数据集太大了
     *
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        if (n <= 3) {
            return true;
        }
        boolean f1 = true, f2 = true, f3 = true;
        for (int i = 4; i <= n; i++) {
            boolean fn = !f1 || !f2 || !f3;
            f1 = f2;
            f2 = f3;
            f3 = fn;
        }
        return f3;
    }

    /**
     * 找规律，从上面dp表达式可以得知
     * 当且仅当连续3个1，下一个才是0。我们定义长度为3的窗口，可以发现如下规律
     *
     * <p>
     * 1110 1110 1110 ...
     * <p>
     * @param n
     * @return
     */
    public boolean canWinNim2(int n) {
        boolean[] arr = {true, true, true, false};
        return arr[(n - 1) % 4];
    }


}
