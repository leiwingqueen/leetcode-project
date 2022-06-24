package com.liyongquan.dp;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

/**
 * 810. 黑板异或游戏
 * <p>
 * <p>
 * 黑板上写着一个非负整数数组 nums[i] 。Alice 和 Bob 轮流从黑板上擦掉一个数字，Alice 先手。如果擦除一个数字后，剩余的所有数字按位异或运算得出的结果等于 0 的话，当前玩家游戏失败。 (另外，如果只剩一个数字，按位异或运算得到它本身；如果无数字剩余，按位异或运算结果为 0。）
 * <p>
 * 换种说法就是，轮到某个玩家时，如果当前黑板上所有数字按位异或运算结果等于 0，这个玩家获胜。
 * <p>
 * 假设两个玩家每步都使用最优解，当且仅当 Alice 获胜时返回 true。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入: nums = [1, 1, 2]
 * 输出: false
 * 解释:
 * Alice 有两个选择: 擦掉数字 1 或 2。
 * 如果擦掉 1, 数组变成 [1, 2]。剩余数字按位异或得到 1 XOR 2 = 3。那么 Bob 可以擦掉任意数字，因为 Alice 会成为擦掉最后一个数字的人，她总是会输。
 * 如果 Alice 擦掉 2，那么数组变成[1, 1]。剩余数字按位异或得到 1 XOR 1 = 0。Alice 仍然会输掉游戏。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 1000
 * 0 <= nums[i] <= 2^16
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chalkboard-xor-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class XorGame {
    /**
     * 回溯算法
     * <p>
     * 时间复杂度O(n!)
     * <p>
     * 超时
     *
     * @param nums
     * @return
     */
    public boolean xorGame(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return backtrace(nums, xor, 0, nums.length);
    }

    private boolean backtrace(int[] nums, int xor, int idx, int len) {
        log.info("idx:{},xor:{}", idx, xor);
        if (idx == len) {
            return xor == 0;
        }
        if (xor == 0) {
            return true;
        }
        for (int i = idx; i < len; i++) {
            swap(nums, idx, i);
            boolean res = backtrace(nums, xor ^ nums[idx], idx + 1, len);
            log.info("idx:{},num:{},xor:{},res:{}", idx, nums[idx], xor ^ nums[idx], res);
            //回溯
            swap(nums, i, idx);
            if (!res) {
                return true;
            }
        }
        return false;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 官解，证明极其难想到
     *
     * @param nums
     * @return
     */
    public boolean xorGame2(int[] nums) {
        if (nums.length % 2 == 0) {
            return true;
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor == 0;
    }
}
