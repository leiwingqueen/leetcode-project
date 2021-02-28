package com.liyongquan.array;

/**
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * <p>
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * <p>
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,2,3]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：[6,5,4,4]
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：[1,3,2]
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：[1,2,4,5]
 * 输出：true
 * 示例 5：
 * <p>
 * 输入：[1,1,1]
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotonic-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsMonotonic {
    public boolean isMonotonic(int[] A) {
        int len = A.length;
        if (len <= 1) {
            return true;
        }
        //确定升序还是降序
        int idx = 1;
        while (idx < len && A[idx] == A[idx - 1]) {
            idx++;
        }
        if (idx == len) {
            return true;
        }
        //遍历剩余的数组
        boolean inc = A[idx] > A[idx - 1];
        for (int i = idx + 1; i < len; i++) {
            if (inc) {
                if (A[i] < A[i - 1]) {
                    return false;
                }
            } else {
                if (A[i] > A[i - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 升序和降序分别判断
     *
     * @param A
     * @return
     */
    public boolean isMonotonic2(int[] A) {
        boolean inc = true, desc = true;
        int len = A.length;
        for (int i = 1; i < len; i++) {
            //升序判断
            if (A[i] < A[i - 1]) {
                inc = false;
            } else if (A[i] > A[i - 1]) {
                desc = false;
            }
        }
        return inc || desc;
    }
}
