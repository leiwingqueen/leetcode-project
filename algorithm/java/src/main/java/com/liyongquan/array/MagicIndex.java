package com.liyongquan.array;

/**
 * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
 * <p>
 * 示例1:
 * <p>
 * 输入：nums = [0, 2, 3, 4, 5]
 * 输出：0
 * 说明: 0下标的元素为0
 * 示例2:
 * <p>
 * 输入：nums = [1, 1, 1]
 * 输出：1
 * 说明:
 * <p>
 * nums长度在[1, 1000000]之间
 * 此题为原书中的 Follow-up，即数组中可能包含重复元素的版本
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/magic-index-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MagicIndex {
    /**
     * 无脑遍历
     *
     * @param nums
     * @return
     */
    public int findMagicIndex(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 假设只有一个答案，可以使用二分法，然而事实上对于这道题是走不通的。
     *
     * @param nums
     * @return
     */
    public int findMagicIndex2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) >> 1;
            if (nums[middle] - middle == 0) {
                return middle;
            } else if (nums[middle] - middle < 0) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return nums[left] == left ? left : -1;
    }

    /**
     * 其实这个解法只是适当剪枝。。。
     * <p>
     * 第二种情况是数组中存在多个满足条件的答案，此时我们发现整个数组不具有任何性质。以 [0,0,2,2,5][0,0,2,2,5] 为例，我们仍进行一次将每个元素减去其自身下标的操作，得到 [0,-1,0,-1,1][0,−1,0,−1,1]。目标是要找到第一个出现的 00，而由于数组中出现 00 的位置不确定，因此无法使用二分查找，但是我们可以依据此来进行一定程度的剪枝，我们剪枝的策略为：
     * <p>
     * 每次我们选择数组的中间元素，如果当前中间元素是满足条件的答案，那么这个位置往后的元素我们都不再考虑，只要寻找左半部分是否有满足条件的答案即可。
     * <p>
     * 否则我们需要查看左半部分是否有满足条件的答案，如果没有的话我们仍然需要在右半边寻找，使用的策略同上。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/magic-index-lcci/solution/mo-zhu-suo-yin-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int findMagicIndex3(int[] nums) {
        return degrace(nums, 0, nums.length - 1);
    }

    private int degrace(int[] nums, int left, int right) {
        if (left > right) {
            return -1;
        }
        if (left == right) {
            return nums[left] == left ? left : -1;
        }
        int middle = (left + right) >> 1;
        if (nums[middle] == middle) {
            int l = degrace(nums, left, middle - 1);
            if (l < 0) {
                return middle;
            }
            return l;
        } else {
            int l = degrace(nums, left, middle - 1);
            if (l >= 0) {
                return l;
            } else {
                return degrace(nums, middle + 1, right);
            }
        }
    }
}
