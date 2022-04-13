package com.liyongquan.weeklycontest.wc233;

//给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数：low 和 high ，请返回 漂亮数对 的数目。
//
//漂亮数对 是一个形如 (i, j) 的数对，其中 0 <= i < j < nums.length 且 low <= (nums[i] XOR nums[j]) <= high 。
//
// 
//
//示例 1：
//
//输入：nums = [1,4,2,7], low = 2, high = 6
//输出：6
//解释：所有漂亮数对 (i, j) 列出如下：
//    - (0, 1): nums[0] XOR nums[1] = 5
//    - (0, 2): nums[0] XOR nums[2] = 3
//    - (0, 3): nums[0] XOR nums[3] = 6
//    - (1, 2): nums[1] XOR nums[2] = 6
//    - (1, 3): nums[1] XOR nums[3] = 3
//    - (2, 3): nums[2] XOR nums[3] = 5
//示例 2：
//
//输入：nums = [9,8,4,2,1], low = 5, high = 14
//输出：8
//解释：所有漂亮数对 (i, j) 列出如下：
//​​​​​    - (0, 2): nums[0] XOR nums[2] = 13
//    - (0, 3): nums[0] XOR nums[3] = 11
//    - (0, 4): nums[0] XOR nums[4] = 8
//    - (1, 2): nums[1] XOR nums[2] = 12
//    - (1, 3): nums[1] XOR nums[3] = 10
//    - (1, 4): nums[1] XOR nums[4] = 9
//    - (2, 3): nums[2] XOR nums[3] = 6
//    - (2, 4): nums[2] XOR nums[4] = 5
// 
//
//提示：
//
//1 <= nums.length <= 2 * 104
//1 <= nums[i] <= 2 * 104
//1 <= low <= high <= 2 * 104
//
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-pairs-with-xor-in-a-range
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class CountPairs {
    public int countPairs(int[] nums, int low, int high) {
        int cnt = 0;
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                int xor = nums[i] ^ nums[j];
                if (xor >= low && xor <= high) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public int countPairs2(int[] nums, int low, int high) {
        int cnt = 0;
        int len = nums.length;
        TrieTree tree = new TrieTree();
        for (int i = 0; i < len; i++) {
            int f1 = tree.find(nums[i], high);
            int f2 = tree.find(nums[i], low - 1);
            cnt += f1 - f2;
            tree.add(nums[i]);
        }
        return cnt;
    }

    public static final int MAX_BIT = 15;

    private class TrieTree {
        protected TrieNode root;

        public TrieTree() {
            root = new TrieNode(0);
        }

        public void add(int num) {
            TrieNode cur = root;
            int bit = 1 << (MAX_BIT - 1);
            while (bit > 0) {
                if ((num & bit) == 0) {
                    if (cur.left == null) {
                        TrieNode node = new TrieNode(1);
                        cur.left = node;
                    } else {
                        cur.left.cnt++;
                    }
                    cur = cur.left;
                } else {
                    if (cur.right == null) {
                        TrieNode node = new TrieNode(1);
                        cur.right = node;
                    } else {
                        cur.right.cnt++;
                    }
                    cur = cur.right;
                }
                bit >>= 1;
            }
        }

        //查找<=mx的数量
        public int find(int num, int mx) {
            TrieNode cur = root;
            int bit = 1 << (MAX_BIT - 1);
            int sum = 0;
            while (cur != null && bit > 0) {
                if (bit == 1) {
                    //最后一层，不需要继续迭代
                    if ((mx & bit) == 0) {
                        if ((num & bit) == 0) {
                            if (cur.left != null) {
                                sum += cur.left.cnt;
                            }
                        } else {
                            if (cur.right != null) {
                                sum += cur.right.cnt;
                            }
                        }
                    } else {
                        sum += cur.cnt;
                    }
                } else {
                    if ((mx & bit) == 0) {
                        //mx对应是0，则xor只能是0
                        if ((num & bit) == 0) {
                            cur = cur.left;
                        } else {
                            cur = cur.right;
                        }
                    } else {
                        //mx对应是1，则xor可以是1和0
                        if ((num & bit) == 0) {
                            if (cur.left != null) {
                                sum += cur.left.cnt;
                            }
                            cur = cur.right;
                        } else {
                            if (cur.right != null) {
                                sum += cur.right.cnt;
                            }
                            cur = cur.left;
                        }
                    }
                }
                bit >>= 1;
            }
            return sum;
        }
    }

    private static class TrieNode {
        TrieNode left;
        TrieNode right;
        int cnt;

        public TrieNode(int cnt) {
            this.cnt = cnt;
        }
    }
}
