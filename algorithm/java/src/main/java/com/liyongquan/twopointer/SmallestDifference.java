package com.liyongquan.twopointer;

import java.util.Arrays;

/**
 * 给定两个整数数组a和b，计算具有最小差绝对值的一对数值（每个数组中取一个值），并返回该对数值的差
 * <p>
 * 示例：
 * <p>
 * 输入：{1, 3, 15, 11, 2}, {23, 127, 235, 19, 8}
 * 输出： 3，即数值对(11, 8)
 * 提示：
 * <p>
 * 1 <= a.length, b.length <= 100000
 * -2147483648 <= a[i], b[i] <= 2147483647
 * 正确结果在区间[-2147483648, 2147483647]内
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-difference-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SmallestDifference {
    /**
     * 二分法。
     * 首先数组长度为10w级别，因此我们的时间复杂度只能考虑O(nlogn)|O(n)，暴力的解法是不可行的。
     * <p>
     * 先尝试对a,b进行排序
     * 我们首先在数组a中确定一个数字，然后在b中找一个最接近的数字(二分查找)。
     * <p>
     * 另外在二分查找的时间复杂度是O(logn)，因此我们可以考虑把较长的数组用于二分。
     * <p>
     * 整体的时间复杂度大概是O(nlogn)
     *
     * @param a
     * @param b
     * @return
     */
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        //小的数组进行二分查找
        int[] arr1, arr2;
        if (a.length <= b.length) {
            arr1 = a;
            arr2 = b;
        } else {
            arr1 = b;
            arr2 = a;
        }
        //二分查找过程
        long min = Integer.MIN_VALUE;
        for (int num1 : arr1) {
            int num2 = binarySearch(arr2, num1);
            long sub = a.length <= b.length ? (long) num1 - num2 : (long) num2 - num1;
            if (sub > Integer.MAX_VALUE || sub < Integer.MIN_VALUE) {
                continue;
            }
            if (Math.abs(sub) < Math.abs(min)) {
                min = sub;
            }
        }
        //这道题本身的用例是有点问题的，题意的结果范围是[-2147483648, 2147483647]，这就意味着有负数的场景，也就是数组的扣减顺序是有要求的。我直观地认为应该是num1-num2，但事实上第一个用例就不过了。
        return (int) Math.abs(min);
    }

    public int binarySearch(int[] arr, int target) {
        int l = 0, r = arr.length - 1;
        if (l == r || arr[l] >= target) {
            return arr[l];
        }
        if (arr[r] <= target) {
            return arr[r];
        }
        //查找<=target的最大值
        while (l < r) {
            int middle = (l + r) / 2;
            if (arr[middle] == target) {
                return arr[middle];
            } else if (arr[middle] < target) {
                //已经找到边界值
                if (arr[middle + 1] > target) {
                    l = middle;
                    break;
                } else {
                    l = middle + 1;
                }
            } else {
                r = middle - 1;
            }
        }
        return (long) target - arr[l] < (long) arr[l + 1] - target ? arr[l] : arr[l + 1];
    }
}
