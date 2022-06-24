package com.liyongquan.trie;

/**
 * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。
 * <p>
 * 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。
 * <p>
 * 你能在O(n)的时间解决这个问题吗？
 * <p>
 * 示例:
 * <p>
 * 输入: [3, 10, 5, 25, 2, 8]
 * <p>
 * 输出: 28
 * <p>
 * 解释: 最大的结果是 5 ^ 25 = 28.
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindMaximumXOR {
    /**
     * 暴力解法
     * <p>
     * 时间复杂度O(n^2)，空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public int findMaximumXOR(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int xor = nums[i] ^ nums[j];
                max = Math.max(max, xor);
            }
        }
        return max;
    }

    /**
     * 对于最大长度为L的数字。我们可以知道他的最大异或的数字。
     * eg.对于数字1001，其最大异或数字为0110。
     * 我们的目标是通过前缀树找到其最大的数字
     * <p>
     * 时间复杂度O(n*l),n是数组的长度，l是每个数字的最大长度
     * 空间复杂度O(2^l)
     *
     * @param nums
     * @return
     */
    public int findMaximumXOR2(int[] nums) {
        //获取最大长度
        int max = 0;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        int len = bitCount(max);
        //构建前缀树
        TrieTree tree = new TrieTree();
        for (int num : nums) {
            tree.append(int2bit(num, len));
        }
        //搜索
        int res = 0;
        for (int num : nums) {
            int xor = tree.findMax(int2bit(num, len));
            res = Math.max(res, xor);
        }
        return res;
    }

    private int bitCount(int num) {
        if (num == 0) {
            return 1;
        }
        int count = 0;
        while (num > 0) {
            count++;
            num >>= 1;
        }
        return count;
    }

    private int[] int2bit(int num, int len) {
        int[] res = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            int mod = num & 0b1;
            res[i] = mod;
            num >>= 1;
        }
        return res;
    }

    private static class TrieTree {
        private TrieNode root;

        public TrieTree() {
            this.root = new TrieNode();
        }

        public void append(int[] bits) {
            TrieNode cur = root;
            for (int bit : bits) {
                cur = cur.append(bit);
            }
        }

        /**
         * 找到最大匹配
         * 时间复杂度O(L)
         *
         * @return
         */
        public int findMax(int[] bits) {
            TrieNode cur = root;
            int res = 0;
            for (int i = 0; i < bits.length; i++) {
                //取反
                int bit = 1 - bits[i];
                int idx = 0;
                if (cur.child[bit] != null) {
                    idx = bit;
                    res += 1 << (bits.length - i - 1);
                } else {
                    idx = 1 - bit;
                }
                cur = cur.child[idx];
            }
            return res;
        }
    }


    private static class TrieNode {
        private TrieNode[] child;
        //所有数字高度都一样，不需要这个属性了
        //private boolean end;

        public TrieNode() {
            child = new TrieNode[2];
        }

        public TrieNode append(int idx) {
            if (child[idx] == null) {
                child[idx] = new TrieNode();
            }
            return child[idx];
        }
    }
}
