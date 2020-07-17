package com.liyongquan.binarySort;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumTrees {
    public int numTrees(int n) {
        return degrace(1, n);
    }

    private int degrace(int start, int end) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return 1;
        }
        int sum = 0;
        for (int i = start; i <= end; i++) {
            int left = degrace(start, i - 1);
            int right = degrace(i + 1, end);
            if (left == 0) {
                sum += right;
            } else if (right == 0) {
                sum += left;
            } else {
                sum += left * right;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        NumTrees trees = new NumTrees();
        int i = trees.numTrees(3);
        System.out.println(i);
    }
}
