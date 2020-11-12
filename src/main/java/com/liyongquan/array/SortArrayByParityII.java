package com.liyongquan.array;

import com.liyongquan.math.HammingWeight;

/**
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * <p>
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * <p>
 * 你可以返回任何满足上述条件的数组作为答案。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SortArrayByParityII {
    /**
     * 两个同向指针同时移动，一个是偶数指针，一个是奇数指针。分别找到不合符的元素进行交换。
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {
        int p1 = 0, p2 = 1;
        while (p1 < A.length - 1 && p2 < A.length) {
            //分别找到第一个奇偶不符的
            while (p1 < A.length - 1 && A[p1] % 2 == 0) {
                p1 += 2;
            }
            while (p2 < A.length && A[p2] % 2 == 1) {
                p2 += 2;
            }
            //满足条件，不需要交换
            if (p1 >= A.length - 1 || p2 >= A.length) {
                return A;
            }
            //交换
            int tmp = A[p1];
            A[p1] = A[p2];
            A[p2] = tmp;
            p1 += 2;
            p2 += 2;
        }
        return A;
    }
}
