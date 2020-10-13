package com.liyongquan.bit;

/**
 * 下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
 * <p>
 * 示例1:
 * <p>
 * 输入：num = 2（或者0b10）
 * 输出：[4, 1] 或者（[0b100, 0b1]）
 * 示例2:
 * <p>
 * 输入：num = 1
 * 输出：[2, -1]
 * 提示:
 * <p>
 * num的范围在[1, 2147483647]之间；
 * 如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/closed-number-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ClosedNumber {
    /**
     * 超时
     * <p>
     * 首先暴力解法是不可能的。
     * <p>
     * 我们可以考虑int除去符号位只有31位。
     * <p>
     * 我们假设前n位有k个1，后31-n位和为m,解为f(n,k,m)。
     * f(n,k,m)=min{f(n-1,k,m),f(n-1,k-1,m+1<<31-n)}
     * <p>
     * 先尝试用递归解法
     *
     * @param num
     * @return
     */
    public int[] findClosedNumbers(int num) {
        int k = 0, tmp = num;
        while (tmp != 0) {
            if ((tmp & 0b1) == 1) {
                k++;
            }
            tmp >>= 1;
        }
        return dfs(31, k, 0, num);
    }

    private int[] dfs(int n, int k, int sum, int num) {
        if (k == 0) {
            if (sum == num) {
                return new int[]{-1, -1};
            } else if (sum > num) {
                return new int[]{sum, -1};
            } else {
                return new int[]{-1, sum};
            }
        }
        if (n == 0) {
            return new int[]{-1, -1};
        }
        //剪枝
        if (n < k) {
            return new int[]{-1, -1};
        }
        int[] r1 = dfs(n - 1, k, sum, num);
        int[] r2 = dfs(n - 1, k - 1, sum + (1 << (31 - n)), num);
        return new int[]{min(r1[0], r2[0]), max(r1[1], r2[1])};
    }

    private int min(int a, int b) {
        if (a == -1 || b == -1) {
            return a == -1 ? b : a;
        }
        return Math.min(a, b);
    }

    private int max(int a, int b) {
        if (a == -1 || b == -1) {
            return a == -1 ? b : a;
        }
        return Math.max(a, b);
    }

    /**
     * 其实这个解法比较难想。。。
     * <p>
     * 比 num 大的数：从右往左，找到第一个 01 位置，然后把 01 转为 10，右侧剩下的 1 移到右侧的低位，右侧剩下的位清0。
     * 比 num 小的数：从右往左，找到第一个 10 位置，然后把 10 转为 01，右侧剩下的 1 移到右侧的高位，右侧剩下的位置0。
     * <p>
     * 常规上，低位在右侧，bitset 注意方向相反
     * <p>
     * 作者：wushaoling
     * 链接：https://leetcode-cn.com/problems/closed-number-lcci/solution/wei-yun-suan-by-wushaoling-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int[] findClosedNumbers2(int num) {
        int pos = 0, big = num, oneCount = 0;
        //最高为31位，但是31位为1的场景不能满足(>num的最小值不存在)
        for (; pos < 30; pos++) {
            //第一个01的位置
            if ((big & (1 << pos)) != 0) {
                if ((big & (1 << (pos + 1))) == 0) {
                    big += (1 << pos);
                    break;
                } else {
                    //当前位清0
                    big -= (1 << pos);
                    oneCount++;
                }
            }
        }
        //找不到01
        if (pos == 30) {
            big = -1;
        } else {
            //右侧的所有1右移
            for (int i = 0; i < oneCount; i++) {
                big += (1 << i);
            }
        }
        //small处理
        pos = 0;
        oneCount = 0;
        int small = num;
        for (; pos < 30; pos++) {
            //第一个10的位置
            if ((small & (1 << pos)) == 0 && (small & (1 << (pos + 1))) != 0) {
                small -= (1 << pos);
                break;
            } else if ((small & (1 << pos)) != 0) {
                oneCount++;
                small -= (1 << pos);
            }
        }
        if (pos == 30) {
            small = -1;
        } else {
            //右侧的所有1左移
            pos--;
            for (int i = 0; i < oneCount; i++) {
                small += (1 << pos);
                pos--;
            }
        }
        return new int[]{big, small};
    }

    public static void main(String[] args) {
        ClosedNumber cn = new ClosedNumber();
        int[] closedNumbers = cn.findClosedNumbers2(2);
        for (int closedNumber : closedNumbers) {
            System.out.println(closedNumber);
        }
        int[] closedNumbers1 = cn.findClosedNumbers2(1);
        for (int i : closedNumbers1) {
            System.out.println(i);
        }
    }
}
