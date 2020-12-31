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
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {

    }

    private List<TreeNode> backtrace(TreeNode root, TreeNode parent, int[] use, int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        if (n == 1) {
            Arrays.asList(new TreeNode(1));
        }
        List<TreeNode> res = new LinkedList<>();
        //初始化根节点
        if (root == null) {
            for (int i = 0; i < n; i++) {
                TreeNode node = new TreeNode(i + 1);
                use[i] = 1;
                List<TreeNode> subRes = backtrace(node, node, use, n);
                res.addAll(subRes);
                //回溯
                use[i] = 0;
            }
        } else {
            //左右子树需要分别处理
            List<TreeNode> left = Collections.emptyList(), right = Collections.emptyList();
            for (int i = 0; i < parent.val - 1; i++) {
                parent.left = new TreeNode(i + 1);
                use[i] = 1;
                left = backtrace(root, parent.left, use, n);
                use[i] = 0;
                parent.left = null;
            }
            for (int i = parent.val; i < n; i++) {
                parent.right = new TreeNode(i + 1);
                use[i] = 1;
                right = backtrace(root, parent.right, use, n);
                use[i] = 0;
                parent.right = null;
            }
            //这里必须做深度拷贝

        }
    }

    private TreeNode copy(TreeNode node) {
        if (node == null) {
            return null;
        }
        TreeNode node1 = new TreeNode(node.val);
        TreeNode left = copy(node.left);
        TreeNode right = copy(node.right);
        node1.left = left;
        node1.right = right;
        return node1;
    }
}
