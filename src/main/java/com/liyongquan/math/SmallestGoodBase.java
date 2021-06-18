package com.liyongquan.math;

import java.math.BigDecimal;

/**
 * 483. 最小好进制
 * <p>
 * 对于给定的整数 n, 如果n的k（k>=2）进制数的所有数位全为1，则称 k（k>=2）是 n 的一个好进制。
 * <p>
 * 以字符串的形式给出 n, 以字符串的形式返回 n 的最小好进制。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："13"
 * 输出："3"
 * 解释：13 的 3 进制是 111。
 * 示例 2：
 * <p>
 * 输入："4681"
 * 输出："8"
 * 解释：4681 的 8 进制是 11111。
 * 示例 3：
 * <p>
 * 输入："1000000000000000000"
 * 输出："999999999999999999"
 * 解释：1000000000000000000 的 999999999999999999 进制是 11。
 *  
 * <p>
 * 提示：
 * <p>
 * n的取值范围是 [3, 10^18]。
 * 输入总是有效且没有前导 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-good-base
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SmallestGoodBase {
    /**
     * 二分查找
     * <p>
     * 错误
     *
     * @param n
     * @return
     */
    public String smallestGoodBase(String n) {
        BigDecimal l = new BigDecimal(3), r = new BigDecimal(n);
        BigDecimal num = new BigDecimal(n);
        while (l.compareTo(r) < 0) {
            BigDecimal mid = l.add(r).divide(new BigDecimal(2), 0, BigDecimal.ROUND_DOWN);
            if (isGoodBase(num, mid)) {
                r = mid;
            } else {
                l = mid.add(BigDecimal.ONE);
            }
        }
        return l.toString();
    }

    private boolean isGoodBase(BigDecimal num, BigDecimal base) {
        while (num.compareTo(BigDecimal.ZERO) > 0) {
            //num%base
            BigDecimal[] div = num.divideAndRemainder(base);
            if (div[1].compareTo(BigDecimal.ONE) != 0) {
                return false;
            }
            //num/=base
            num = num.divide(base, 0, BigDecimal.ROUND_DOWN);
        }
        return true;
    }

    public String smallestGoodBase2(String n) {
        long num = Long.parseLong(n);
        //求最大的数字长度log2(n)
        int maxLen = (int) (Math.log(num) / Math.log(2) + 1);
        for (int len = maxLen; len >= 2; len--) {
            //k<n^(1/len)<k+1
            double pow = Math.pow(num, 1.0 / (len - 1));
            //判断k是否正整数
            int k = (int) pow;
            if (Math.ceil(pow) == Math.floor(pow)) {
                k -= 1;
            }
            int res = 0;
            for (int i = 0; i < len; i++) {
                res = res * k + 1;
            }
            if (res == num) {
                return String.valueOf(k);
            }
        }
        //实际上不会到这里
        return "";
    }
}
