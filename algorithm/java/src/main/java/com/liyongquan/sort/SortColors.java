package com.liyongquan.sort;


import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-colors
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortColors {
    /**
     * 直接排序算法
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        Arrays.sort(nums);
    }

    /**
     * 计数器
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param nums
     */
    public void sortColors2(int[] nums) {
        //统计
        int c1 = 0, c2 = 0, c3 = 0;
        for (int num : nums) {
            if (num == 0) {
                c1++;
            } else if (num == 1) {
                c2++;
            } else {
                c3++;
            }
        }
        //更新
        for (int i = 0; i < nums.length; i++) {
            if (i < c1) {
                nums[i] = 0;
            } else if (i < c1 + c2) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    /**
     * 双指针
     * 其实边界情况不是很好处理
     *
     * @param nums
     */
    public void sortColors3(int[] nums) {
        int left = 0, right = nums.length - 1, i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                //swap
                nums[i++] = nums[left];
                nums[left++] = 0;
            } else if (nums[i] == 2) {
                //swap
                nums[i] = nums[right];
                nums[right--] = 2;
            } else {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        SortColors sc = new SortColors();
        int[] nums = {2, 0, 2, 1, 1, 0};
        sc.sortColors3(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }
}
