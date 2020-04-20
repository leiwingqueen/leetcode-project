package com.liyongquan.array;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumCounter3 {
    /**
     * 我们先假设一个结论：
     * 当某一位的1的总和不为3的倍数，则只出现一次的数字在该位必然为1。
     * <p>
     * 我们使用反证法证明：
     * 若只出现一次的数字在某一位上为0，那么某一位的总和必然为3的倍数
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int bit = 1;
        int result = 0;
        for (int i = 0; i < 31; i++) {
            int sum = 0;
            for (int num : nums) {
                sum += (num & bit) == 0 ? 0 : 1;
            }
            if (sum % 3 != 0) {
                result += bit;
            }
            bit <<= 1;
        }
        return result;
    }
}
