package com.liyongquan.math;

import java.util.Arrays;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 5 * 106
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-primes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountPrimes {
    /**
     * 暴力解法，会超时
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 埃氏筛，这是一种古老的算法了，还是佩服老前辈
     * <p>
     * 时间复杂度是O(nloglogn)
     */
    public int countPrimes2(int n) {
        int count = 0;
        int[] prime = new int[n];
        Arrays.fill(prime, 1);
        double sqrt = Math.sqrt(n);
        for (int i = 2; i < n; i++) {
            if (prime[i] == 1) {
                count++;
                //这里乘法可能会导致溢出，我们需要做个简单处理
                if (i < sqrt) {
                    //把i的所有倍数都标记
                    for (int j = i * i; j < n; j += i) {
                        prime[j] = 0;
                    }
                }
            }
        }
        return count;
    }
}
