package com.liyongquan.slidewindow;

import java.util.Map;

/**
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 * <p>
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 * <p>
 * 返回 A 中好子数组的数目。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 * <p>
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 * 通过次数10,369提交次数27,545
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SubarraysWithKDistinct {
    /**
     * 滑动窗口算法
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMost(A, K) - atMost(A, K - 1);
    }

    /**
     * 用具体的例子理解：最多包含 3 种不同整数的子区间 [1, 3, 2, 3] （双指针算法是在左边界固定的前提下，让右边界走到最右边），当前可以确定 1 开始的满足最多包含 3 种不同整数的子区间有 [1]、[1, 3]、[1, 3, 2]、[1, 3, 2, 3]。
     *
     * 所有的 左边界固定前提下，根据右边界最右的下标，计算出来的子区间的个数就是整个函数要返回的值。用右边界固定的前提下，左边界最左边的下标去计算也是完全可以的。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers/solution/k-ge-bu-tong-zheng-shu-de-zi-shu-zu-by-l-ud34/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 这个就有点难理解了
     * @param A
     * @param K
     * @return
     */
    public int atMost(int[] A, int K) {
        if (K == 0) {
            return 0;
        }
        //频数统计
        int len = A.length;
        int[] freq = new int[len + 1];
        int cnt = 0;
        int l = 0, r = 0;
        int res = 0;
        while (r < len) {
            //右指针移动
            if (freq[A[r]] == 0) {
                cnt++;
            }
            freq[A[r]]++;
            r++;
            //判断左指针是否需要移动
            if (cnt > K) {
                //一直移动到减少一个数字
                while (cnt > K) {
                    freq[A[l]]--;
                    if (freq[A[l]] == 0) {
                        cnt--;
                    }
                    l++;
                }
            }
            //这里是关键中的关键
            res += r - l;
        }
        return res;
    }
}
