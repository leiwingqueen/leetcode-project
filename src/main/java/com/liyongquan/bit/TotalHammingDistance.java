package com.liyongquan.bit;

/**
 * 477. 汉明距离总和
 * <p>
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * <p>
 * 计算一个数组中，任意两个数之间汉明距离的总和。
 * <p>
 * 示例:
 * <p>
 * 输入: 4, 14, 2
 * <p>
 * 输出: 6
 * <p>
 * 解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
 * 所以答案为：
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * 注意:
 * <p>
 * 数组中元素的范围为从 0到 10^9。
 * 数组的长度不超过 10^4。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/total-hamming-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TotalHammingDistance {
    /**
     * 暴力解法，肯定超时
     *
     * @param nums
     * @return
     */
    public int totalHammingDistance(int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                cnt += distance(nums[i], nums[j]);
            }
        }
        return cnt;
    }

    private int distance(int a, int b) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            if ((a & (1 << i)) != (b & (1 << i))) {
                cnt++;
            }
        }
        return cnt;
    }
}
