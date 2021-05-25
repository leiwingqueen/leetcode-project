package com.liyongquan.bit;

/**
 * 1787. 使所有区间的异或结果为零
 * <p>
 * 给你一个整数数组 nums​​​ 和一个整数 k​​​​​ 。区间 [left, right]（left <= right）的 异或结果 是对下标位于 left 和 right（包括 left 和 right ）之间所有元素进行 XOR 运算的结果：nums[left] XOR nums[left+1] XOR ... XOR nums[right] 。
 * <p>
 * 返回数组中 要更改的最小元素数 ，以使所有长度为 k 的区间异或结果等于零。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,0,3,0], k = 1
 * 输出：3
 * 解释：将数组 [1,2,0,3,0] 修改为 [0,0,0,0,0]
 * 示例 2：
 * <p>
 * 输入：nums = [3,4,5,2,1,7,3,4,7], k = 3
 * 输出：3
 * 解释：将数组 [3,4,5,2,1,7,3,4,7] 修改为 [3,4,7,3,4,7,3,4,7]
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,4,1,2,5,1,2,6], k = 3
 * 输出：3
 * 解释：将数组[1,2,4,1,2,5,1,2,6] 修改为 [1,2,3,1,2,3,1,2,3]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= nums.length <= 2000
 * ​​​​​​0 <= nums[i] < 210
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/make-the-xor-of-all-segments-equal-to-zero
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class XorMinChanges {
    /**
     * 贪心
     * <p>
     * 解答错误，有可能改动两个数值是最优解
     *
     * @param nums
     * @param k
     * @return
     */
    public int minChanges(int[] nums, int k) {
        int xor = 0;
        for (int i = 0; i < k; i++) {
            xor ^= nums[i];
        }
        if (xor == 0) {
            int cnt = 0;
            for (int j = 0; j + k < nums.length; j++) {
                if (nums[j] != nums[j + k]) {
                    cnt++;
                }
            }
            return cnt;
        } else {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                nums[i] ^= xor;
                int cnt = 1;
                for (int j = 0; j + k < nums.length; j++) {
                    if (nums[j] != nums[j + k]) {
                        nums[j + k] = nums[j];
                        cnt++;
                    }
                }
                min = Math.min(min, cnt);
            }
            return min;
        }
    }


}
