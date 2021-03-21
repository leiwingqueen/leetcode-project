package com.liyongquan.array;

/**
 * 面试题 17.04. 消失的数字
 * <p>
 * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
 * <p>
 * 注意：本题相对书上原题稍作改动
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,0,1]
 * 输出：2
 *  
 * <p>
 * 示例 2：
 * <p>
 * 输入：[9,6,4,2,3,5,7,0,1]
 * 输出：8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-number-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MissingNumber {
    /**
     * 0到n的数字的和
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        //0,1,2,...,len的和
        double cnt = (len + 1) / 2d;
        int sum = (int) (len * cnt);
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        return sum - s;
    }
}
