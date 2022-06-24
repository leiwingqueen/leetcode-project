package com.liyongquan.array;

import java.util.LinkedList;
import java.util.List;

/**
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * <p>
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * 示例 2：
 * <p>
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * 示例 3：
 * <p>
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * 示例 4：
 * <p>
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ArrayFormOfInteger {
    /**
     * 模拟加法运算，加法运算是从低位开始运算的，我们需要一个队列的结构，每次从队列的头部添加数据
     * <p>
     * 时间复杂度O(n),空间复杂度O(1)[不包含返回结果]
     *
     * @param A
     * @param K
     * @return
     */
    public List<Integer> addToArrayForm(int[] A, int K) {
        LinkedList<Integer> res = new LinkedList<>();
        int plus = 0, idx = A.length - 1;
        while (K > 0 || idx >= 0) {
            int num = idx >= 0 ? A[idx] : 0;
            int sum = num + K % 10 + plus;
            if (sum >= 10) {
                res.offerFirst(sum - 10);
                plus = 1;
            } else {
                res.offerFirst(sum);
                plus = 0;
            }
            K /= 10;
            idx--;
        }
        if (plus == 1) {
            res.offerFirst(1);
        }
        return res;
    }
}
