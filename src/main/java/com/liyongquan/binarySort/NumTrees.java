package com.liyongquan.binarySort;

import java.util.concurrent.ForkJoinPool;

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
    /**
     * 正常的递归解法会超时，这里尝试似乎dp的方式解题
     * f(m,n)=sum(f(m,i-1)*f(i+1,n)),其中m<=i<=n
     * 时间复杂度O(n^3)
     *
     * @param n
     * @return
     */
    public int numTrees2(int n) {
        int[][] result = new int[n + 2][n + 2];
        //对角线、横线、斜线赋值
        for (int i = 0; i < n + 2; i++) {
            result[i][i] = 1;
            result[0][i] = 1;
            result[i][0] = 1;
            result[i][n + 1] = 1;
            result[n + 1][i] = 1;
        }
        //下半区域赋值
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j <= i; j++) {
                result[i][j] = 1;
            }
        }
        int col = 1;
        while (col <= n) {
            int i = 1;
            while (col + i - 1 <= n) {
                //计算result[i][col+i-1]的值
                int sum = 0;
                for (int j = i; j <= col + i - 1; j++) {
                    sum += result[i][j - 1] * result[j + 1][col + i - 1];
                }
                result[i][col + i - 1] = sum;
                i++;
            }
            col++;
        }
        return result[1][n];
    }

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
        int i = trees.numTrees2(3);
        System.out.println(i);
    }
}
