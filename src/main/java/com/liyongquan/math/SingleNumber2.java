package com.liyongquan.math;

/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *  
 * <p>
 * 限制：
 * <p>
 * 2 <= nums.length <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SingleNumber2 {
    public int[] singleNumbers(int[] nums) {
        //抑或汇总
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum ^= nums[i];
        }
        //取某一位非0的位进行分组
        int bit = 1;
        while ((sum & bit) == 0) {
            bit <<= 1;
        }
        //把两个不重复的数子分在不同组，相同的数组分在相同组
        int g1 = 0, g2 = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if ((num & bit) == 0) {
                g1 ^= num;
            } else {
                g2 ^= num;
            }
        }
        return new int[]{g1, g2};
    }
}
