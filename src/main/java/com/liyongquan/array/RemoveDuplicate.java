package com.liyongquan.array;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 给定数组 nums = [1,1,2],
 * <p>
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * <p>
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicate {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        }
        int count = nums.length;
        int pre = nums[0];
        for (int i = 1; i < count; ) {
            if (nums[i] == pre) {
                //后面的所有数字往前移动
                for (int j = i + 1; j < count; j++) {
                    nums[j - 1] = nums[j];
                }
                count--;
            } else {
                pre = nums[i];
                i++;
            }
        }
        return count;
    }

    /**
     * 双指针，太巧妙了
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        if (nums.length <= 0) {
            return 0;
        }
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[j] != nums[i]) {
                nums[j + 1] = nums[i];
                j++;
            }
        }
        return j+1;
    }
}
