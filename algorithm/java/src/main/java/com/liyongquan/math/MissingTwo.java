package com.liyongquan.math;

/**
 * 面试题 17.19. 消失的两个数字
 * <p>
 * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
 * <p>
 * 以任意顺序返回这两个数字均可。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1]
 * 输出: [2,3]
 * 示例 2:
 * <p>
 * 输入: [2,3]
 * 输出: [1,4]
 * 提示：
 * <p>
 * nums.length <= 30000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-two-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MissingTwo {
    public int[] missingTwo(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int sum2 = (len + 2) * (len + 3) / 2;
        int diff = sum2 - sum;
        //缺失的两个数必然在divide的两侧，剩下的问题就是找缺失的一个数字
        int divide = diff / 2;
        int sum3 = 0;
        for (int num : nums) {
            if (num <= divide) {
                sum3 += num;
            }
        }
        int a = (1 + divide) * divide / 2 - sum3;
        return new int[]{a, diff - a};
    }
}
