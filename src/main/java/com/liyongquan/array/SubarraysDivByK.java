package com.liyongquan.array;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 974. 和可被 K 整除的子数组
 * <p>
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：A = [4,5,0,-2,-3,1], K = 5
 * 输出：7
 * 解释：
 * 有 7 个子数组满足其元素之和可被 K = 5 整除：
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class SubarraysDivByK {
    /**
     * 继续套模板
     * <p>
     * 前缀和+hashmap
     * <p>
     * f(i,j)=prefixSum[j]-prefixSum[i]
     * =>
     * (prefixSum[j]-prefixSum[i])%k=0
     * =>
     * prefixSum[j]%k-prefixSum[i]%k=0
     * <p>
     * prefixSum[j]%k=prefixSum[i]%k
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK(int[] A, int K) {
        int len = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int prefixSum = 0, res = 0;
        for (int i = 0; i < len; i++) {
            prefixSum = (prefixSum + A[i]) % K;
            //负数转成正数，方便统一处理
            if (prefixSum < 0) {
                prefixSum += K;
            }
            res += map.getOrDefault(prefixSum, 0);
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return res;
    }
}
