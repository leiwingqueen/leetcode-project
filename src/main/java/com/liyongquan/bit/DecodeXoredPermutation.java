package com.liyongquan.bit;

/**
 * 1734. 解码异或后的排列
 * <p>
 * 给你一个整数数组 perm ，它是前 n 个正整数的排列，且 n 是个 奇数 。
 * <p>
 * 它被加密成另一个长度为 n - 1 的整数数组 encoded ，满足 encoded[i] = perm[i] XOR perm[i + 1] 。比方说，如果 perm = [1,3,2] ，那么 encoded = [2,1] 。
 * <p>
 * 给你 encoded 数组，请你返回原始数组 perm 。题目保证答案存在且唯一。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：encoded = [3,1]
 * 输出：[1,2,3]
 * 解释：如果 perm = [1,2,3] ，那么 encoded = [1 XOR 2,2 XOR 3] = [3,1]
 * 示例 2：
 * <p>
 * 输入：encoded = [6,5,4,6]
 * 输出：[2,4,1,5,3]
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= n < 105
 * n 是奇数。
 * encoded.length == n - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-xored-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DecodeXoredPermutation {
    public int[] decode(int[] encoded) {
        int len = encoded.length;
        int s = 0;
        for (int i = 1; i <= len + 1; i++) {
            s ^= i;
        }
        //求第一个元素的值
        for (int i = 1; i < len; i += 2) {
            s ^= encoded[i];
        }
        //根据第一个值反推原加密数据
        int[] decode = new int[len + 1];
        decode[0] = s;
        for (int i = 1; i <= len; i++) {
            decode[i] = decode[i - 1] ^ encoded[i - 1];
        }
        return decode;
    }
}
