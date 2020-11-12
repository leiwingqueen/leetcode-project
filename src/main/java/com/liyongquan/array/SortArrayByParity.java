package com.liyongquan.array;

/**
 * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
 * <p>
 * 你可以返回满足此条件的任何数组作为答案。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortArrayByParity {
    /**
     * 双指针解法，头尾指针
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParity(int[] A) {
        int l = 0, r = A.length - 1;
        while (l < r) {
            while (l < r && A[l] % 2 == 0) {
                l++;
            }
            while (l < r && A[r] % 2 == 1) {
                r--;
            }
            if (l < r) {
                int tmp = A[l];
                A[l] = A[r];
                A[r] = tmp;
                l++;
                r--;
            }
        }
        return A;
    }
}
