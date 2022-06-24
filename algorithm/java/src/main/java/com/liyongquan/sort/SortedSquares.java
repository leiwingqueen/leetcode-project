package com.liyongquan.sort;

import sun.text.resources.cldr.ii.FormatData_ii;

import java.util.Arrays;

/**
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 * <p>
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortedSquares {
    /**
     * 朴素解法
     * <p>
     * 时间复杂度O(n*logn)
     *
     * @param A
     * @return
     */
    public int[] sortedSquares(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] *= A[i];
        }
        Arrays.sort(A);
        return A;
    }

    /**
     * 正数和负数分开处理，类似归并排序
     * 时间复杂度O(2n)
     *
     * @param A
     * @return
     */
    public int[] sortedSquares2(int[] A) {
        int[] positive = new int[A.length], negetive = new int[A.length], result = new int[A.length];
        int p1 = 0, p2 = 0, p3 = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                positive[p1++] = A[i] * A[i];
            } else {
                negetive[p2++] = A[i] * A[i];
            }
        }
        //正数是升序，负数为倒序，合并两个数组
        int i = 0, j = p2 - 1;
        while (p3 < A.length) {
            if (i >= p1 || (j >= 0 && positive[i] > negetive[j])) {
                result[p3++] = negetive[j--];
            } else {
                result[p3++] = positive[i++];
            }
        }
        return result;
    }

    /**
     * 双指针解法
     *
     * @param A
     * @return
     */
    public int[] sortedSquares3(int[] A) {
        int[] result = new int[A.length];
        //找到正负数的分界点
        int middle = 0;
        for (; middle < A.length; middle++) {
            if (A[middle] > 0) {
                break;
            }
        }
        //从中间向两边移动
        int i = middle - 1, j = middle, p = 0;
        while (p < A.length) {
            if (j >= A.length || (i >= 0 && A[j] >= -A[i])) {
                result[p++] = A[i] * A[i];
                i--;
            } else {
                result[p++] = A[j] * A[j];
                j++;
            }
        }
        return result;
    }
}
