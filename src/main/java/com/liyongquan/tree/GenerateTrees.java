package com.liyongquan.tree;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * <p>
 * 1         3     3      2      1
 * \       /     /      / \      \
 * 3     2     1      1   3      2
 * /     /       \                 \
 * 2     1         2                 3
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GenerateTrees {
    /**
     * 递归解法是一开始最容易想到的算法。回溯解法
     *
     * 时间复杂度。指数级别
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        if (n == 1) {
            return Arrays.asList(new TreeNode(1));
        }
        List<TreeNode> res = new LinkedList<>();
        int[] used = new int[n];
        for (int i = 0; i < n; i++) {
            used[i] = 1;
            res.addAll(backtrace(new TreeNode(i + 1), used, n));
            used[i] = 0;
        }
        return res;
    }

    private List<TreeNode> backtrace(TreeNode parent, int[] used, int n) {
        List<TreeNode> res = new LinkedList<>();
        //左右子树需要分别处理
        List<TreeNode> left = null, right = null;
        for (int i = 0; i < parent.val - 1; i++) {
            if (used[i] == 0) {
                parent.left = new TreeNode(i + 1);
                used[i] = 1;
                left = backtrace(parent.left, used, n);
                used[i] = 0;
                parent.left = null;
            }
        }
        for (int i = parent.val; i < n; i++) {
            if (used[i] == 0) {
                parent.right = new TreeNode(i + 1);
                used[i] = 1;
                right = backtrace(parent.right, used, n);
                used[i] = 0;
                parent.right = null;
            }
        }
        //剩下就是排列组合的问题，这里必须做深度拷贝
        if (left == null && right == null) {
            res.add(new TreeNode(parent.val));
        } else if (left == null) {
            for (TreeNode treeNode : right) {
                TreeNode nr = new TreeNode(parent.val);
                nr.right = treeNode;
                res.add(nr);
            }
        } else if (right == null) {
            for (TreeNode treeNode : left) {
                TreeNode nr = new TreeNode(parent.val);
                nr.left = treeNode;
                res.add(nr);
            }
        } else {
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode nr = new TreeNode(parent.val);
                    nr.left = l;
                    nr.right = r;
                    res.add(nr);
                }
            }
        }
        return res;
    }
}
